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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  前端控制器
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
    @Resource
    private IDDCRoleAuthService roleAuthService;
    @Resource
    private IDDCAdminService adminService;
    @Resource
    private IDDCAuthService authService;


    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCRole> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                           @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                           String start, String end, String keywords) throws Exception {
        Wrapper<DDCRole> wrapper = new EntityWrapper<>();
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

        wrapper = wrapper.gt("role_level", 1);
        Page<DDCRole> page0 = roleService.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        if(!CollectionUtils.isEmpty(page0.getRecords())){
            List<DDCAdmin> list;
            String adminName;
            for(DDCRole role : page0.getRecords()){
                list = adminService.selectByRoleId(role.getId());
                if(!list.isEmpty()) {
                    for (DDCAdmin admin : list) {
                        adminName = admin.getName();
                        if (!StringUtils.isEmpty(role.getAdminName())) {
                            role.setAdminName(role.getAdminName() + ", " + adminName);
                        } else {
                            role.setAdminName(adminName);
                        }
                    }
                }else{
                    role.setAdminName("暂无可用用户");
                }
            }
        }
        ResponsePageModel<DDCRole> page = ResponsePageHelper.buildResponseModel(page0);
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
            roleService.deleteBatchIds(idArray);
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>("删除失败", ResponseModel.FAIL.getCode());

        }

    }

    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCRole entity,
                                             @CurrentUser DDCAdmin admin) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean bool = false;
        DDCRoleAuth roleAuth;

        if (entity.getId() == null) {
            bool = true;
            entity.setId(0L);
            entity.setCreateBy(admin.getId());
            entity.setCreateTime(data.format(new Date(System.currentTimeMillis())));
            entity.setDelFlag(0);
        }
        entity.setUpdateBy(admin.getId());
        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
        roleService.insertOrUpdate(entity);
        String[] strs = entity.getAuthId().split(",");
        for (int i = 0; i < strs.length; i++) {
            long authId = Long.parseLong(strs[i]);
            roleAuth = new DDCRoleAuth(entity.getId(),authId);
            roleAuth.setId(0L);
            if(!bool){
                roleAuthService.delRoleAuthByRoleId(entity.getId());
                roleAuthService.insert(roleAuth);
            }else{
                roleAuth.setRoleId(roleService.selectOne(new EntityWrapper<DDCRole>().eq("name",entity.getName())).getId());
                roleAuthService.insert(roleAuth);
            }
        }

        return ResponseHelper.buildResponseModel("操作成功");
    }

    //list转换树形结构。暂未使用。
    @RequestMapping("/getAuths")
    @ResponseBody
    public ResponseModel<List<AuthNode>> getAuths(@CurrentUser DDCAdmin admin) throws Exception {
        DDCRole currentRole = roleService.selectById(admin.getRoleId());
        List<AuthNode> list = new ArrayList<>(10);
        List<DDCAuth> topAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                                                        .gt("auth_level", currentRole.getRoleLevel())
                                                        .eq("level", 1)
                                                        .eq("del_flag", 0));
        if(!CollectionUtils.isEmpty(topAuths)){
            for(DDCAuth auth:topAuths){
                List<AuthNode> nodes=new ArrayList<>();
                List<DDCAuth> secondAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                                                                    .gt("auth_level", currentRole.getRoleLevel())
                                                                    .eq("level", 2)
                                                                    .eq("del_flag", 0)
                                                                    .eq("p_id", auth.getId()));
                List<AuthNode> nodes2=new ArrayList<>(10);
                if(!CollectionUtils.isEmpty(secondAuths)){
                    for(DDCAuth secondAuth:secondAuths){
                        List<AuthNode> nodes3=new ArrayList<>(10);
                        List<DDCAuth> opAuths = authService.selectList(new EntityWrapper<DDCAuth>()
                                                                        .gt("auth_level", currentRole.getRoleLevel())
                                                                        .eq("level", 3)
                                                                        .eq("del_flag", 0)
                                                                        .eq("p_id", secondAuth.getId())
                                );
                        if(!CollectionUtils.isEmpty(opAuths)){
                            for(DDCAuth opAuth:opAuths){
                                nodes3.add(new AuthNode(opAuth,null));
                            }
                        }
                        nodes2.add(new AuthNode(secondAuth,nodes3));
                    }
                }
                list.add(new AuthNode(auth,nodes2));
            }
        }
        return ResponseHelper.buildResponseModel(list);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static  class AuthNode{
        public DDCAuth auth;
        private List<AuthNode> nodes;
    }

}

