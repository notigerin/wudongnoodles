package com.ddc.server.shiro;

import com.ddc.server.base.Constant;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.exception.UnauthorizedException;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.IDDCAuthService;
import com.ddc.server.service.IDDCRoleService;
import com.ddc.server.service.SpringContextBeanService;
import com.ddc.server.util.ComUtil;
import com.ddc.server.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dingpengfei
 * @since 2019-05-09
 */
public class MyRealm extends AuthorizingRealm {
    private IDDCAdminService  adminService;
    private IDDCAuthService authService;
    private IDDCRoleService roleService;
    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JWTToken;
//    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (roleService == null) {
            this.roleService = SpringContextBeanService.getBean(IDDCRoleService.class);
        }
        if (authService == null) {
            this.authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        }


        String userNo = JWTUtil.getUserNo(principals.toString());
        DDCAdmin admin = adminService.selectByName(userNo);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        /*
        Role role = roleService.selectOne(new EntityWrapper<Role>().eq("role_code", adminToRole.getRoleCode()));
        //添加控制角色级别的权限
        Set<String> roleNameSet = new HashSet<>();
        roleNameSet.add(role.getRoleName());
        simpleAuthorizationInfo.addRoles(roleNameSet);
        */
        //控制菜单级别按钮  类中用@RequiresPermissions("admin:list") 对应数据库中code字段来控制controller
        ArrayList<String> pers = new ArrayList<>();
        List<DDCAuth> authList = authService.selectByRoleId(admin.getRoleId());
        for (DDCAuth auth : authList) {
             if (!ComUtil.isEmpty(auth.getFlag())) {
                  pers.add(String.valueOf(auth.getFlag()));
              }
        }
        Set<String> permission = new HashSet<>(pers);
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws UnauthorizedException {
//        if (adminService == null) {
//            this.adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//        }
//        String token = (String) auth.getCredentials();
//        if(Constant.isPass){
//            return new SimpleAuthenticationInfo(token, token, this.getName());
//        }
//        // 解密获得username，用于和数据库进行对比
//        String userNo = JWTUtil.getUserNo(token);
//        if (userNo == null) {
//            throw new UnauthorizedException("token invalid");
//        }
//        DDCAdmin adminBean = adminService.selectByName(userNo);
//        if (adminBean == null) {
//            throw new UnauthorizedException("Admin didn't existed!");
//        }
//        if (! JWTUtil.verify(token, userNo, adminBean.getPassword())) {
//            throw new UnauthorizedException("Username or password error");
//        }
//        return new SimpleAuthenticationInfo(token, token, this.getName());
//    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        if(adminService==null){
            adminService=SpringContextBeanService.getBean(IDDCAdminService.class);
        }
        // 获取用户的输入的账号.
        String name = (String) token.getPrincipal();
        System.out.println(token.getCredentials());

        // 通过name和password从数据库中查找 User对象，
        DDCAdmin admin = adminService.selectByName(name);
        if (admin == null) {
            return null;
        }


        // 加密方式;
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        String password = admin.getPassword();
        // 秘钥
        ByteSource salt = ByteSource.Util.bytes(admin.getSalt());
        // 当前域的名称（MyShiroRealm）
        String realmName = getName();
        // 认证信息

        return new SimpleAuthenticationInfo(admin, password, salt, realmName);
    }

}
