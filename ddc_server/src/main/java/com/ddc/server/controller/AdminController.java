package com.ddc.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCRole;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.IDDCRoleService;
import com.ddc.server.shiro.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端控制器
 *
 * @author MuQ
 * @since 2019-06-19
 */
@RequestMapping("/admin")
@Controller
@Slf4j
public class AdminController {
    @Resource
    private IDDCAdminService adminService;
    @Resource
    private IDDCRoleService roleService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCAdmin> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                            String start, String end, String keywords) throws Exception {
        Wrapper<DDCAdmin> wrapper = new EntityWrapper<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(start)) {
            wrapper = wrapper.ge("create_time", simpleDateFormat.parse(start).getTime());
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper = wrapper.le("create_time", simpleDateFormat.parse(end).getTime());
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper = wrapper.like("name", keywords);
        }
        Page<DDCAdmin> adminPage=adminService.selectPage(new Page<>(pageNumber, pageSize),
                wrapper);
        if(!CollectionUtils.isEmpty(adminPage.getRecords())){
            for(DDCAdmin admin:adminPage.getRecords()){
                DDCRole role=roleService.selectById(admin.getRoleId());
                admin.setRoleName(role==null?"无":role.getName());
            }
        }
        ResponsePageModel<DDCAdmin> page = ResponsePageHelper.buildResponseModel(adminPage);
        return page;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public ResponseModel<String> delete(@RequestParam String ids) throws Exception {
        String[] arr = ids.split(",");
        List<Long> idArray = new ArrayList<>(5);
        for (int i = 0; i < arr.length; i++) {
            if (!StringUtils.isEmpty(arr[i]) && org.apache.commons.lang3.StringUtils.isNumeric(arr[i])) {
                idArray.add(Long.valueOf(arr[i]));
            }
        }
        if (!CollectionUtils.isEmpty(idArray)) {
            adminService.deleteBatchIds(idArray);
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>(
                    "删除失败", ResponseModel.FAIL.getCode()
            );

        }

    }

    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCAdmin entity,
                                             @CurrentUser DDCAdmin admin) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (entity.getId() == null) {
            entity.setCreateBy(admin.getId());
            entity.setCreateTime(data.format(new Date(System.currentTimeMillis())));
            entity.setDelFlag(0);
            if(StringUtils.isEmpty(entity.getPassword())){
                entity.setPassword("123456");

            }
            PasswordUtils.entryptPassword(entity);
        }else {
            if(StringUtils.isEmpty(entity.getPassword())){
                DDCAdmin dbAdmin=adminService.selectById(entity.getId());
                entity.setPassword(dbAdmin.getPassword());
                entity.setSalt(dbAdmin.getSalt());
            }else {
                PasswordUtils.entryptPassword(entity);
            }
        }
        entity.setUpdateBy(admin.getId());
        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
        adminService.insertOrUpdate(entity);

        return ResponseHelper.buildResponseModel("操作成功");
    }


//    /**
//    * 获取所有管理员列表
//    *
//    * @return
//    * @throws Exception
//    */
//    @RequestMapping("/list")
//    @ResponseBody
//    public ResponseModel<List<DDCAdmin>> AdminList(HttpServletResponse resp){
//        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//        List<DDCAdmin> list = adminService.selectAllAdmin();
//        return ResponseHelper.buildResponseModel(list);
//    }
//
//
//    /**
//    * 添加Admin
//    *
//    * @param request
//    * @param name 用户名
//    * @param password 密码
//    * @param sex 性别
//    * @param mobile 电话
//    * @param email 游戏
//    * @param roleId 角色
//    * @param model
//    * @throws Exception
//    * @return
//    */
//    @RequestMapping("/addAdmin")
//    @ResponseBody
//    public ResponseModel<String> insertAdd(HttpServletRequest request, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password, @RequestParam(value = "confirmPassword",required = false) String confirmPassword,
//                                         @RequestParam(value = "sex",required = false) Integer sex, @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
//                                         @RequestParam(value = "roleId",required = false) long roleId, @RequestParam(value = "remark",required = false) String remark, HttpSession session , Model model) throws Exception {
//        String msg;
//        if(StringUtils.isEmpty(password) && StringUtils.isEmpty(confirmPassword)){
//            password ="123456";
//            confirmPassword = "123456";
//        }
//        if(password.equals(confirmPassword)) {
//            DDCAdmin admin = new DDCAdmin(name, password, sex, mobile, email, roleId, remark);
//            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//            adminService.insertAdmin(admin);
//            msg = "添加成功";
//        }else{
//            msg = "两次密码不一致！";
//        }
//        return ResponseHelper.buildResponseModel(msg);
//    }
//
//
//    @RequestMapping("/modifyAdmin")
//    @ResponseBody
//    public ResponseModel<String> modifyAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) long id, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password, @RequestParam(value = "confirmPassword",required = false) String confirmPassword,
//                                         @RequestParam(value = "sex",required = false) Integer sex, @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
//                                         @RequestParam(value = "roleId",required = false) long roleId, @RequestParam(value = "remark",required = false) String remark, HttpSession session , Model model) throws Exception {
//        String msg;
//        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//        if(StringUtils.isEmpty(password) && StringUtils.isEmpty(confirmPassword)){
//            DDCAdmin admin = new DDCAdmin(id, name, sex, mobile, email, roleId, remark);
//            adminService.updateAdmin(admin);
//            msg = "添加成功";
//            return ResponseHelper.buildResponseModel(msg);
//        }
//        if(password.equals(confirmPassword)) {
//            DDCAdmin admin = new DDCAdmin(id, name, password, sex, mobile, email, roleId, remark);
//            adminService.updateAdmin(admin);
//            msg = "添加成功";
//        }else{
//            msg = "两次密码不一致！";
//        }
//        return ResponseHelper.buildResponseModel(msg);
//    }
//
//
//    @RequestMapping("/updateStatus")
//    @ResponseBody
//    public String updateStatus(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id , @RequestParam(value = "status",required = false) Integer status) throws Exception {
//        String msg;
//        if (id != null && status != null) {
//            if (status == 0) {
//                status = 1;
//            }else {
//                status = 0;
//            }
//            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//            adminService.updateStatus(id, status);
//        }
//        return "/page/admin-list";
//    }
//
//    @RequestMapping("/delAdmin")
//    @ResponseBody
//    public ResponseModel<String> delAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id) throws Exception {
//        String msg;
//        if (id != null) {
//            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
//            adminService.delAdmin(id);
//            msg = "删除成功";
//        }else{
//            msg = "数据出错";
//        }
//        return ResponseHelper.buildResponseModel(msg);
//    }
//
//
//    @RequestMapping("/batchDel")
//    @ResponseBody
//    public void batchDeletes(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="delItems") String delItems) {
//        String[] strs = delItems.split(",");
//        for (int i = 0; i < strs.length; i++) {
//            long oid = Long.parseLong(strs[i]);
//            adminService.delAdmin(oid);
//        }
//    }

}

