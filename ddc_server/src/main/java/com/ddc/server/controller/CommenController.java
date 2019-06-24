package com.ddc.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.Commen;
import com.ddc.server.entity.DDCAdmin;

import com.ddc.server.service.IDDCAdminService;

import com.ddc.server.service.IDDCCommenService;
import com.ddc.server.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * 登录接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@RequestMapping("/commen")
@Controller
public class CommenController {
    @Resource
    private IDDCCommenService commenService;


    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<Commen> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                          @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                          String start, String end, String keywords) throws Exception {
        Wrapper<Commen> wrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(start)) {
            wrapper = wrapper.ge("create_time", start);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper = wrapper.le("create_time", end);
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper = wrapper.like("username", keywords);
        }
        ResponsePageModel<Commen> page = ResponsePageHelper.buildResponseModel(
                commenService.selectPage(new Page<>(pageNumber, pageSize),
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
            commenService.deleteBatchIds(idArray);
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>(
                    "删除失败", ResponseModel.FAIL.getCode()
            );

        }

    }
    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody Commen commen,
                                             @CurrentUser DDCAdmin admin) throws Exception {
        if(commen.getId()!=0){
            commen.setCreateBy(admin.getId());
            commen.setCreateTime(System.currentTimeMillis());

        }
            commen.setUpdateBy(admin.getId());
            commen.setUpdateTime(System.currentTimeMillis());
            commenService.insertOrUpdate(commen);

            return ResponseHelper.buildResponseModel("操作成功");
        }
    }


