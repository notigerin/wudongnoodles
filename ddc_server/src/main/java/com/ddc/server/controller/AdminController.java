package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.SpringContextBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    /**
    * 获取所有管理员列表
    *
    * @return
    * @throws Exception
    */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseModel<List<DDCAdmin>> AdminList(HttpServletResponse resp){
        adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
        List<DDCAdmin> list = adminService.selectAllAdmin();
        return ResponseHelper.buildResponseModel(list);
    }


    /**
    * 添加Admin
    *
    * @param request
    * @param name 用户名
    * @param password 密码
    * @param sex 性别
    * @param mobile 电话
    * @param email 游戏
    * @param roleId 角色
    * @param model
    * @throws Exception
    * @return
    */
    @RequestMapping("/addAdmin")
    @ResponseBody
    public ResponseModel<String> insertAdd(HttpServletRequest request, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password, @RequestParam(value = "confirmPassword",required = false) String confirmPassword,
                                         @RequestParam(value = "sex",required = false) Integer sex, @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
                                         @RequestParam(value = "roleId",required = false) long roleId, @RequestParam(value = "remark",required = false) String remark, HttpSession session , Model model) throws Exception {
        String msg;
        if(password.equals(confirmPassword)) {
            DDCAdmin admin = new DDCAdmin(name, password, sex, mobile, email, roleId, remark);
            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
            adminService.insertAdmin(admin);
            msg = "添加成功";
        }else{
            msg = "两次密码不一致！";
        }
        return ResponseHelper.buildResponseModel(msg);
    }


    @RequestMapping("/modifyAdmin")
    @ResponseBody
    public ResponseModel<String> modifyAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) long id, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "password",required = false) String password, @RequestParam(value = "confirmPassword",required = false) String confirmPassword,
                                         @RequestParam(value = "sex",required = false) Integer sex, @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
                                         @RequestParam(value = "roleId",required = false) long roleId, @RequestParam(value = "remark",required = false) String remark, HttpSession session , Model model) throws Exception {
        String msg;
        if(password.equals(confirmPassword)) {
            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
            DDCAdmin admin = new DDCAdmin(id, name, password, sex, mobile, email, roleId, remark);
            adminService.updateAdmin(admin);
            msg = "添加成功";
        }else{
            msg = "两次密码不一致！";
        }
        return ResponseHelper.buildResponseModel(msg);
    }


    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id , @RequestParam(value = "status",required = false) Integer status) throws Exception {
        String msg;
        if (id != null && status != null) {
            if (status == 0) {
                status = 1;
            }else {
                status = 0;
            }
            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
            adminService.updateStatus(id, status);
        }
        return "/page/admin-list";
    }

    @RequestMapping("/delAdmin")
    @ResponseBody
    public ResponseModel<String> delAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id) throws Exception {
        String msg;
        if (id != null) {
            adminService = SpringContextBeanService.getBean(IDDCAdminService.class);
            adminService.delAdmin(id);
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
            adminService.delAdmin(oid);
        }
    }

}

