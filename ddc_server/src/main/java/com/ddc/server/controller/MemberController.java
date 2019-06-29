package com.ddc.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.annotation.CurrentUser;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.DDCMember;
import com.ddc.server.service.IDDCMemberService;
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
import java.util.*;

@RequestMapping("/member")
@Controller
@Slf4j
public class MemberController {
    @Resource
    private IDDCMemberService memberService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCMember> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                            String start, String end, String keywords) throws Exception {

        Wrapper<DDCMember> wrapper = new EntityWrapper<>();
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

        wrapper = wrapper.eq("is_delete", 0);
        Page<DDCMember> page0 = memberService.selectPage(new Page<>(pageNumber, pageSize), wrapper);
        ResponsePageModel<DDCMember> page = ResponsePageHelper.buildResponseModel(page0);
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
            memberService.deleteBatchIds(idArray);
            return ResponseHelper.buildResponseModel("删除成功");
        } else {
            return new ResponseModel<String>(
                    "删除失败", ResponseModel.FAIL.getCode()
            );

        }

    }

    @RequestMapping("/updateOrAdd")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCMember entity,
                                             @CurrentUser DDCMember admin) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (entity.getId() == null) {
            entity.setId(0L);
            entity.setCreater(admin.getId());
            entity.setRegisterTime(data.format(new Date(System.currentTimeMillis())));
            entity.setIsDelete(0);
            if(StringUtils.isEmpty(entity.getPassword())){
                entity.setPassword("123456");

            }
        }else {
            if(StringUtils.isEmpty(entity.getPassword())){
                DDCMember dbmember=memberService.selectById(entity.getId());
                entity.setPassword(dbmember.getPassword());
            }
        }
        entity.setCreater(admin.getId());
        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
        memberService.insertOrUpdate(entity);

        return ResponseHelper.buildResponseModel("操作成功");
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResponseModel<String> updateStatus(@RequestParam(value = "id",required = false) Long id , @RequestParam(value = "status",required = false) Integer status) throws Exception {
        if (id != null && status != null) {
            if (status == 0) {
                status = 1;
            } else {
                status = 0;
            }
            memberService.updateStatus(id, status);
        }
        return ResponseHelper.buildResponseModel("操作成功");

    }

}