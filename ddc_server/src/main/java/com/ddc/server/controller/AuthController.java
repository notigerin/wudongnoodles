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
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.service.IDDCAuthService;
import com.ddc.server.service.SpringContextBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private IDDCAuthService authService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCAuth> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                           @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                           String start, String end, String keywords) throws Exception {
        Wrapper<DDCAuth> wrapper = new EntityWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(start)) {
            wrapper = wrapper.ge("create_time", simpleDateFormat.parse(start).getTime());
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper = wrapper.le("create_time", simpleDateFormat.parse(end).getTime());
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper = wrapper.like("name", keywords);
        }
        ResponsePageModel<DDCAuth> page = ResponsePageHelper.buildResponseModel(
                authService.selectPage(new Page<>(pageNumber, pageSize),
                        wrapper));
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
            authService.deleteBatchIds(idArray);
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>(
                    "删除失败", ResponseModel.FAIL.getCode()
            );

        }

    }

    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCAuth entity,
                                             @CurrentUser DDCAdmin admin) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (entity.getId() == null) {
            entity.setId(0L);
            entity.setCreateBy(admin.getId());
            entity.setCreateTime(data.format(new Date(System.currentTimeMillis())));
            entity.setDelFlag(0);
        }
        entity.setUpdateBy(admin.getId());
        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
        authService.insertOrUpdate(entity);

        return ResponseHelper.buildResponseModel("操作成功");
    }



    @RequestMapping("/authList")
    @ResponseBody
    public ResponseModel<List<DDCAuth>> AuthList(HttpServletResponse resp){
        authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        List<DDCAuth> list = authService.selectAllAuth();
        return ResponseHelper.buildResponseModel(list);

    }

//    @RequestMapping("/addAuth")
//    @ResponseBody
//    public void addAuth(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "flag",required = false) String flag, @RequestParam(value = "pId",required = false) Long pId){
//        Integer level;
//        if(pId == 0){
//            level = 1;
//        }else{
//            level = authService.selectById(pId).getLevel() + 1;
//        }
//        DDCAuth auth = new DDCAuth(name,flag,pId,level);
//        authService.insertAuth(auth);
//    }
//
//    @RequestMapping("/delAuth")
//    @ResponseBody
//    public ResponseModel<String> delAuth(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id) throws Exception {
//        String msg;
//        if (id != null) {
//            authService = SpringContextBeanService.getBean(IDDCAuthService.class);
//            authService.delAuth(id);
//            msg = "删除成功";
//        }else{
//            msg = "数据出错";
//        }
//        return ResponseHelper.buildResponseModel(msg);
//    }
//
//    @RequestMapping("/admin-auth-modify")
//    @ResponseBody
//    public ModelAndView findAuth(@RequestParam(value="id") Long id, HttpSession session){
//        DDCAuth auth = authService.selectById(id);
//        session.setAttribute("auth",auth);
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("admin-auth-modify");
//        return modelAndView;
//    }
//
//    @RequestMapping("/modifyAuth")
//    @ResponseBody
//    public void modifyAuth(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "flag",required = false) String flag, @RequestParam(value = "pId",required = false) Long pId, HttpSession session , Model model){
//        Integer level;
//        if(pId == 0){
//            level = 1;
//        }else{
//            level = authService.selectById(pId).getLevel() + 1;
//        }
//        DDCAuth auth = new DDCAuth(id,name,flag,pId,level);
//        authService.updateAuth(auth);
//    }
//
//    @RequestMapping("/batchDel")
//    @ResponseBody
//    public void batchDeletes(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="delItems") String delItems) {
//        String[] strs = delItems.split(",");
//        for (int i = 0; i < strs.length; i++) {
//            long oid = Long.parseLong(strs[i]);
//            authService.delAuth(oid);
//        }
//    }
}
