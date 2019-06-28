package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCCategories;
import com.ddc.server.service.IDDCCategoriesService;
import com.ddc.server.service.SpringContextBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/Categories")
@Controller
@Slf4j
public class CategoriesController {
    @Resource
    private IDDCCategoriesService categoriesService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponseModel<List<DDCCategories>> CategoriesList(HttpServletResponse resp){
        categoriesService = SpringContextBeanService.getBean(IDDCCategoriesService.class);
        List<DDCCategories> list = categoriesService.selectAllCategories();
        return ResponseHelper.buildResponseModel(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseModel<String> addCategories(HttpServletRequest request, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "pId",required = false) Long pId, @RequestParam(value = "remark",required = false) String remark){
        String msg;
        DDCCategories categories = new DDCCategories(name,remark);
        categories.setPId(pId);
        categoriesService = SpringContextBeanService.getBean(IDDCCategoriesService.class);
        categoriesService.addCategories(categories);
        msg = "添加成功";
        return ResponseHelper.buildResponseModel(msg);
    }


}
