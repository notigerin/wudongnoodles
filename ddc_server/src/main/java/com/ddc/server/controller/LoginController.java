package com.ddc.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ddc.server.annotation.Log;
import com.ddc.server.annotation.Pass;
import com.ddc.server.annotation.ValidationParam;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.service.IDDCAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Controller
public class LoginController {
    @Resource
    private IDDCAdminService adminService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestParam(value = "username",required = false) String username,
                        @RequestParam(value = "password",required =false ) String password,
                        HttpSession session, Model model) throws Exception {
        System.out.println("LoginController.login()");

//         登录失败从request中获取shiro处理的异常信息。
//         shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            }  else {
                msg = "else >> " + exception;
                System.out.println("else -- >" + exception);
            }
            model.addAttribute("msg", msg);
        }else {
            session.setAttribute("currentUser",(DDCAdmin)SecurityUtils.getSubject().getPrincipal());
        }




        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println(subject.getPrincipal()+"退出登录！");
        return "login";
    }

}
