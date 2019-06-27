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
import com.ddc.server.entity.DDCRole;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.service.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    @Resource
    private IDDCRoleAuthService roleAuthService;
    @Resource
    private IDDCRoleService roleService;

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
        Page<DDCAuth> page0 = authService.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        if(!CollectionUtils.isEmpty(page0.getRecords())){
            for(DDCAuth auth : page0.getRecords()){
                if(auth.getPId() == null || auth.getPId() == 0L){
                    auth.setPName("无");
                }else{
                    DDCAuth pAuth = authService.selectById(auth.getPId());
                    if(pAuth == null){
                        auth.setPName("未知");
                    }else{
                        auth.setPName(pAuth.getName());
                    }
                }
            }
        }

        ResponsePageModel<DDCAuth> page = ResponsePageHelper.buildResponseModel(page0);
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
            roleAuthService.delete(new EntityWrapper<DDCRoleAuth>().in("auth_id",ids));
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>("删除失败", ResponseModel.FAIL.getCode());
        }

    }

    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCAuth entity,
                                             @CurrentUser DDCAdmin admin) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean bool = false;
        if (entity.getId() == null) {
            bool = true;
            entity.setId(0L);
            entity.setCreateBy(admin.getId());
            entity.setCreateTime(data.format(new Date(System.currentTimeMillis())));
            entity.setDelFlag(0);
        }
        entity.setUpdateBy(admin.getId());
        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
        authService.insertOrUpdate(entity);
        if(bool){
            DDCRole rootRole = roleService.selectOne(
                    new EntityWrapper<DDCRole>().eq("name","超级管理员"));
            if(rootRole!=null){
                DDCAuth auth = authService.selectOne(new EntityWrapper<DDCAuth>().eq("name",entity.getName()));
                DDCRoleAuth roleAuth = new DDCRoleAuth(0L,rootRole.getId(),auth.getId());
                roleAuthService.insert(roleAuth);
            }
        }
        return ResponseHelper.buildResponseModel("操作成功");
    }

    @RequestMapping("/authList")
    @ResponseBody
    public ResponseModel<List<DDCAuth>> AuthList(HttpServletResponse resp){
        authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        List<DDCAuth> list = authService.selectAllAuth();
        return ResponseHelper.buildResponseModel(list);

    }

}
