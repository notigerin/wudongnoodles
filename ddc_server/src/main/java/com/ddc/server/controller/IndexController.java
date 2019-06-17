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
import org.springframework.web.servlet.ModelAndView;

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
}
