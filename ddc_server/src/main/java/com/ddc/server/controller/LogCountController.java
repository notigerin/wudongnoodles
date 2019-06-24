package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCLogCount;
import com.ddc.server.service.IDDCLogCountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@RequestMapping("/logCount")
public class LogCountController {
    @Resource
    private IDDCLogCountService service;

    @RequestMapping({"charts_1"})
    @ResponseBody
    public ResponseModel<ArrayList<DDCLogCount>> selectAll(){
        ArrayList<DDCLogCount> list=service.selectAll();
        for (DDCLogCount d:list){
            System.out.println(d);
        }
        System.out.println(ResponseHelper.buildResponseModel(list));
        return ResponseHelper.buildResponseModel(list);
    }
}