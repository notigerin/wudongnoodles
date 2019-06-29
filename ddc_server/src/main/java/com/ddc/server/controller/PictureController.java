package com.ddc.server.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.DDCMember;
import com.ddc.server.entity.DDCPicture;
import com.ddc.server.service.IDDCPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/picture")
@Controller
@Slf4j
public class PictureController {
    @Resource
    private IDDCPictureService pictureService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponsePageModel<DDCPicture> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
                                             @RequestParam(name = "limit", required = false, defaultValue = "10") Integer pageSize,
                                             String start, String end, String keywords) throws Exception {

        Wrapper<DDCPicture> wrapper = new EntityWrapper<>();
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

        wrapper = wrapper.eq("del_flag", 0);
        Page<DDCPicture> page0 = pictureService.selectPage(new Page<>(pageNumber, pageSize), wrapper);

        ResponsePageModel<DDCPicture> page = ResponsePageHelper.buildResponseModel(page0);
        return page;
    }


//    @RequestMapping("/delete")
//    @ResponseBody
//    public ResponseModel<String> delete(@RequestParam String ids) throws Exception {
//        String[] arr = ids.split(",");
//        List<Long> idArray = new ArrayList<>(5);
//        for (int i = 0; i < arr.length; i++) {
//            if (!StringUtils.isEmpty(arr[i]) && org.apache.commons.lang3.StringUtils.isNumeric(arr[i])) {
//                idArray.add(Long.valueOf(arr[i]));
//            }
//        }
//        if (!CollectionUtils.isEmpty(idArray)) {
//            pictureService.deleteBatchIds(idArray);
//            return ResponseHelper.buildResponseModel("删除成功");
//        } else {
//            return new ResponseModel<String>(
//                    "删除失败", ResponseModel.FAIL.getCode()
//            );
//
//        }
//
//    }

//    @RequestMapping("/updateOrAdd")
//    @ResponseBody
//    public ResponseModel<String> updateOrAdd(@RequestBody DDCMember entity,
//                                             @CurrentUser DDCAdmin admin) throws Exception {
//        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        if (entity.getId() == null) {
//            entity.setId(0L);
//            entity.setCreater(entity.getId());
//            entity.setRegisterTime(data.format(new Date(System.currentTimeMillis())));
//            entity.setIsDelete(0);
//            if (StringUtils.isEmpty(entity.getPassword())) {
//                entity.setPassword("123456");
//
//            }
//        } else {
//            if (StringUtils.isEmpty(entity.getPassword())) {
//                DDCMember dbmember = pictureService.selectById(entity.getId());
//                entity.setPassword(dbmember.getPassword());
//            }
//        }
//        entity.setCreater(entity.getId());
//        entity.setUpdateTime(data.format(new Date(System.currentTimeMillis())));
//        pictureService.insertOrUpdate(entity);
//
//        return ResponseHelper.buildResponseModel("操作成功");
//    }

//    @RequestMapping("/updateStatus")
//    @ResponseBody
//    public ResponseModel<String> updateStatus(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "status", required = false) Integer status) throws Exception {
//        if (id != null && status != null) {
//            if (status == 0) {
//                status = 1;
//            } else {
//                status = 0;
//            }
//            pictureService.updateStatus(id, status);
//        }
//        return ResponseHelper.buildResponseModel("操作成功");
//
//    }


//    @ResponseBody
//    @RequestMapping("/upload")
//    public String uploadAreaFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
//        String schoolId = request.getParameter("schoolId");//获取参数
//        Map<String,Object> result = new HashMap<String, Object>();
//        try{
//
//            File newfile = new File("e://GitHubProject/ddc-parent/ddc_server/src/main/resources/static/image/" + file.getOriginalFilename());
//            String url = "/image/" + file.getOriginalFilename();
//            //上传文件方法，这里需要改成自己项目里上传文件方法
//            newfile.createNewFile();
//            file.transferTo(newfile);
//            String filePath = newfile.getAbsolutePath();
//            result.put("code", 0);
//            result.put("msg", "上传成功");
//            result.put("filePath", url);
//            return JSON.toJSONString(result);
//        }catch(Exception e){
//            result.put("code", -1);
//            result.put("msg", "上传失败");
//            result.put("filePath", "");
//            return JSON.toJSONString(result);
//        }
//
//    }

}
