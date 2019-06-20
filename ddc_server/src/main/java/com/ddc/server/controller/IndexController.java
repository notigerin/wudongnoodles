package com.ddc.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.annotation.Pass;
import com.ddc.server.entity.DDCAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {

    @RequestMapping({ "/", "/index" })
    @Pass
    public ModelAndView index() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/page/{view}")
    @RequiresUser
//    @Pass
    public ModelAndView page(@PathVariable String view,@CurrentUser DDCAdmin admin) {
        log.info(JSONObject.toJSONString(admin));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName(view);
        return modelAndView;
    }
    @RequestMapping("/page/admin-modify")
    @RequiresUser
//    @Pass
    public ModelAndView adminModify(@RequestParam(value = "id",required = false) long id, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "confirmPassword",required = false) String confirmPassword,
                                    @RequestParam(value = "sex",required = false) Integer sex, @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
                                    @RequestParam(value = "roleId",required = false) long roleId, @RequestParam(value = "remark",required = false) String remark, HttpSession session) {
        DDCAdmin admin = new DDCAdmin(id, name, sex, mobile, email, roleId, remark);
        log.info(JSONObject.toJSONString(admin));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin-modify");
        session.setAttribute("admin",admin);
        return modelAndView;
    }
}
