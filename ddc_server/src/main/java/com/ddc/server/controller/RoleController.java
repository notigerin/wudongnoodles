package com.ddc.server.controller;


import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCRole;
import com.ddc.server.service.IDDCRoleService;
import com.ddc.server.service.SpringContextBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MuQ
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Resource
    private IDDCRoleService roleService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponseModel<List<DDCRole>> RoleList(HttpServletResponse resp){
        roleService = SpringContextBeanService.getBean(IDDCRoleService.class);
        List<DDCRole> list = roleService.selectAllRole();
        for(int i = 0; i < list.size()-1; i++){
            for(int j = i + 1; j < list.size(); j++){
                if(list.get(i).getId().equals(list.get(j).getId())){
                    list.get(i).setAdminName(list.get(i).getAdminName() + "," + list.get(j).getAdminName());
                    list.remove(j);
                    j--;
                }
            }
        }
        return ResponseHelper.buildResponseModel(list);
    }

    @RequestMapping("/delRole")
    @ResponseBody
    public ResponseModel<String> delAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id) throws Exception {
        String msg;
        if (id != null) {
            roleService = SpringContextBeanService.getBean(IDDCRoleService.class);
            roleService.delRole(id);
            msg = "删除成功";
        }else{
            msg = "数据出错";
        }
        return ResponseHelper.buildResponseModel(msg);
    }


    @RequestMapping("/batchDel")
    @ResponseBody
    public void batchDeletes(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="delItems") String delItems) {
        String[] strs = delItems.split(",");
        for (int i = 0; i < strs.length; i++) {
            long oid = Long.parseLong(strs[i]);
            roleService.delRole(oid);
        }
    }



//    @RequestMapping("/roleName")
//    @ResponseBody
//    public ResponseModel<List<DDCRole>> RoleName(HttpServletResponse resp){
//        List<DDCRole> list = roleService.getRoleName();
//        return ResponseHelper.buildResponseModel(list);
//    }


//    @Resource
//    private IRoleService roleService;
//
//    /**
//     *  角色列表
//     */
//    @GetMapping("/pageList")
//    //拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
//    //@RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
//    public ResponseModel<Page<Role>> getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
//                                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize){
//        //根据姓名查分页
////        Page<Role> rolePage = roleService.selectPage(new Page<>(pageIndex, pageSize),
////                new EntityWrapper<Role>().where("role_name like {0}","%"+name+"%"));
//        return ResponseHelper.buildResponseModel(roleService.selectPage(new Page<>(pageIndex, pageSize)));
//    }
//
//    /**
//     *  获取所有角色
//     */
//    @GetMapping("/all")
//    public  ResponseModel<List<Role>> getAllRole(){
//        return ResponseHelper.buildResponseModel(roleService.selectList(new EntityWrapper<Role>()));
//    }
//
//    /**
//     * 获取角色详细信息
//     */
//    @GetMapping(value = "/{roleCode}")
//    public ResponseModel getById(@PathVariable("roleCode") String roleCode)throws Exception{
//        return ResponseHelper.buildResponseModel(roleService.selectByRoleCode(roleCode));
//    }
//
//    /**
//     * 删除角色
//     */
//    @DeleteMapping(value = "/{roleCode}")
//    //拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
//    //@RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
//    public ResponseModel deleteRole(@PathVariable("roleCode") String roleCode)throws Exception{
//        roleService.deleteByRoleCode(roleCode);
//        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
//    }
//
//    /**
//     * 添加角色
//     * @param roleModel
//     * @return
//     */
//    @PostMapping
//    public ResponseModel addRole(RoleModel roleModel) throws Exception{
//        return ResponseHelper.buildResponseModel(roleService.addRoleAndPermission(roleModel));
//    }
//
//    /**
//     * 修改角色信息
//     */
//    @PutMapping
//    public ResponseModel updateRole(RoleModel roleModel) throws Exception{
//        roleService.updateRoleInfo(roleModel);
//        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
//    }



}

