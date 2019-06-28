package com.ddc.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.annotation.Pass;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.entity.DDCRole;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.IDDCAuthService;
import com.ddc.server.service.IDDCRoleAuthService;
import com.ddc.server.service.IDDCRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
    @Resource
    private IDDCRoleService roleService;
    @Resource
    private IDDCRoleAuthService roleAuthService;
    @Resource
    private IDDCAdminService adminService;
    @Resource
    private IDDCAuthService authService;


    @RequestMapping({ "/", "/index" })
    @Pass
    public ModelAndView index(@CurrentUser DDCAdmin admin, HttpSession session) {
        ModelAndView modelAndView=new ModelAndView();

/*        DDCRole currentRole = roleService.selectById(admin.getRoleId());
        List<DDCRoleAuth> roleAuths=roleAuthService.selectList(new EntityWrapper<DDCRoleAuth>()
                .eq("role_id",currentRole.getId()));

        Set<Long> authIds = new HashSet<>();
        if(!CollectionUtils.isEmpty(roleAuths)){
            for(DDCRoleAuth ra : roleAuths){
                authIds.add(ra.getAuthId());
            }
        }
        List<RoleController.AuthNode> list = new ArrayList<>(10);
        List<DDCAuth> topAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                .gt("auth_level", currentRole.getRoleLevel())
                .eq("level", 1)
                .eq("del_flag", 0));
        if(!CollectionUtils.isEmpty(topAuths)){
            for(DDCAuth auth:topAuths){
                List<RoleController.AuthNode> nodes=new ArrayList<>();
                List<DDCAuth> secondAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                        .gt("auth_level", currentRole.getRoleLevel())
                        .eq("level", 2)
                        .eq("del_flag", 0)
                        .eq("p_id", auth.getId()));
                List<RoleController.AuthNode> nodes2=new ArrayList<>(10);
                if(!CollectionUtils.isEmpty(secondAuths)){
                    for(DDCAuth secondAuth:secondAuths){
                        List<RoleController.AuthNode> nodes3=new ArrayList<>(10);
                        List<DDCAuth> opAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                                .gt("auth_level", currentRole.getRoleLevel())
                                .eq("level", 3)
                                .eq("del_flag", 0)
                                .eq("p_id", secondAuth.getId())
                        );
                        if(!CollectionUtils.isEmpty(opAuths)){
                            for(DDCAuth opAuth:opAuths){
                                nodes3.add(new RoleController.AuthNode(opAuth,null));
                            }
                        }
                        nodes2.add(new RoleController.AuthNode(secondAuth,nodes3));
                    }
                }
                list.add(new RoleController.AuthNode(auth,nodes2));
            }
        }
        session.setAttribute("list",list);*/
        //modelAndView.addObject("list",list);
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
