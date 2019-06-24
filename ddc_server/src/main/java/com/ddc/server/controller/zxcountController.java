package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCZxCount;
import com.ddc.server.service.IDDCZxCountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/log1")
public class zxcountController {
    @Resource
    private IDDCZxCountService service;

    @RequestMapping({"charts_2"})
    @ResponseBody
    public ResponseModel<ArrayList<DDCZxCount>> selectAll(){
        ArrayList<DDCZxCount> list=service.selectAll();
        for (DDCZxCount d:list){
            System.out.println(d);
        }
        System.out.println(ResponseHelper.buildResponseModel(list));
        return ResponseHelper.buildResponseModel(list);
    }
}