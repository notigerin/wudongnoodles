package com.ddc.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.DDCBrowseRecords;
import com.ddc.server.service.IDDCBrowseRecordsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前端控制器
 *
 * @author MuQ
 * @since 2019-06-30
 */
@RestController
@RequestMapping("/browseRecords")
public class DDCBrowseRecordsController {
    @Resource
    private IDDCBrowseRecordsService browseRecordsService;


    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCBrowseRecords> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                                    @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                                    String start, String end, String keywords) throws Exception {

        Wrapper<DDCBrowseRecords> wrapper = new EntityWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(start)) {
            wrapper = wrapper.ge("visit_time", simpleDateFormat.parse(start).getTime());
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper = wrapper.le("visit_time", simpleDateFormat.parse(end).getTime());
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper = wrapper.like("user_id", keywords);
        }

        Page<DDCBrowseRecords> page0 = browseRecordsService.selectPage(new Page<>(pageNumber, pageSize), wrapper);

        ResponsePageModel<DDCBrowseRecords> page = ResponsePageHelper.buildResponseModel(page0);
        return page;
    }

    @RequestMapping("/Add")
    @ResponseBody
    public ResponseModel<String> updateOrAdd(@RequestBody DDCBrowseRecords entity) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (entity.getId() == null) {
            entity.setId(0L);
        }
        entity.setVisitTime(data.format(new Date(System.currentTimeMillis())));
        browseRecordsService.insertOrUpdate(entity);
        return ResponseHelper.buildResponseModel("操作成功");
    }

}
