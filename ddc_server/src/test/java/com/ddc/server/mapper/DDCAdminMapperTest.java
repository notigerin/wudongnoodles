package com.ddc.server.mapper;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.entity.DDCRole;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.IDDCAuthService;
import com.ddc.server.service.IDDCRoleService;
import com.ddc.server.service.SpringContextBeanService;
import com.ddc.server.shiro.PasswordUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class DDCAdminMapperTest extends BaseTest {
    @Resource
    private DDCAdminMapper ddcAdminMapper;
    @Resource
    private DDCAuthMapper authMapper;
    @Resource
    private DDCRoleMapper roleMapper;
    @Resource
    private DDCRoleAuthMapper roleAuthMapper;
    @Resource
    private IDDCAdminService adminService;
    @Resource
    private IDDCAuthService authService;
    @Resource
    private IDDCRoleService roleService;


    //@Test
    public void hello() {
//        authMapper.insert(new DDCAuth("资讯管理","ZXGL",0L,1));
//        authMapper.insert(new DDCAuth("评论管理","PLGL",0L,1));
//        authMapper.insert(new DDCAuth("会员管理","HYGL",0L,1));
//        authMapper.insert(new DDCAuth("管理员管理","GLYGL",0L,1));
//        authMapper.insert(new DDCAuth("数据统计","SJTJ",0L,1));
//        authMapper.insert(new DDCAuth("系统管理","XTGL",0L,1));
        authMapper.insert(new DDCAuth("资讯列表", "ZXGL-ZXLB", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("图片管理", "ZXGL-TPGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("栏目管理", "ZXGL-LMGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("分类管理", "ZXGL-FLGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("意见反馈", "PLGL-YJFK", 1139936538944192514L, 2));
        authMapper.insert(new DDCAuth("会员列表", "PLGL-HYLB", 1139936539090993153L, 2));
        authMapper.insert(new DDCAuth("浏览记录", "PLGL-LLJL", 1139936539090993153L, 2));
        authMapper.insert(new DDCAuth("管理员列表", "GLYGL-GLYLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("角色列表", "GLYGL-JSLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("权限列表", "GLYGL-QXLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("活跃用户统计", "SJTJ-HYYH", 1139936539653029890L, 2));
        authMapper.insert(new DDCAuth("浏览记录统计", "SJTJ-LJL", 1139936539653029890L, 2));
        authMapper.insert(new DDCAuth("系统日志", "XTGL-XTRZ", 1139936539791441922L, 2));
    }

    //@Test
    public void hello1() {
        List<DDCAuth> auths = authMapper.selectList(new EntityWrapper<>());
        List<DDCRoleAuth> list = new ArrayList<>(auths.size());
        for (DDCAuth auth : auths) {
            roleAuthMapper.insert(new DDCRoleAuth(null, 1L, auth.getId()));
        }

    }

    //@Test
    public void hello2() {
        DDCAdmin admin = new DDCAdmin("ShuNing", "980613", 1,
                "13000480320", "ShuNing@qq.com", 3L);
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        adminService.insertAdmin(admin);
    }

    //查询Admin获取Role测试
    //@Test
    public void RoleId(){
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        DDCAdmin admin = adminService.selectByName("oneT");
        System.out.println(admin.getRoleId());
    }

    //@Test
    public void AllRole(){
        roleService = SpringContextBeanService.getBean(IDDCRoleService.class);
        List<DDCRole> list = roleMapper.getRoleList();
        for(DDCRole role : list){
            System.out.println(role.getName());
        }
    }

    //Admin列表查询测试
    @Test
    public void AllAdmin(){
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        roleService = SpringContextBeanService.getBean(IDDCRoleService.class);
        List<DDCAdmin> list = adminService.selectAllAdmin();
        List<DDCRole> roleList = roleMapper.getRoleList();
        for(DDCAdmin admin : list) {
            if(admin.getStatus() != 2) {
                System.out.print(admin.getName() + "," + admin.getMobile() + "," + admin.getRoleId() + ",");
                for(DDCRole role : roleList){
                    if(role.getId().equals(admin.getRoleId())){
                        System.out.print(role.getName());
                    }
                }
                if (admin.getStatus() == 0) {
                    System.out.println("已启用");
                } else if (admin.getStatus() == 1) {
                    System.out.println("未启用");
                }
            }
        }
    }

    //删除功能测试
    //@Test
    public void delAdmin(){
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        DDCAdmin admin = adminService.selectByName("oneT");
        long id = admin.getId();
        adminService.delAdmin(id);
    }

    //@Test
    public void updateAdmin(){
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        DDCAdmin admin = adminService.selectByName("ShuNing");
        admin.setMobile("13000000613");
        adminService.updateAdmin(admin);
    }

    //@Test
    public void AuthNameOfRoleList(){
        authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        DDCAdmin admin = adminService.selectByName("ShuNing");
        List<DDCAuth> list = authService.selectByRoleId(admin.getRoleId());
        for(DDCAuth auth :list){
            System.out.println(auth.getName() + "," + auth.getLevel());
        }
    }
}
