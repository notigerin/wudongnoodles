package com.ddc.server.controller;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.service.IDDCAuthService;
import com.ddc.server.service.SpringContextBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器
 *
 * @author MuQ
 * @since 2019-06-19
 */
@RequestMapping("/auth")
@Controller
@Slf4j
public class AuthController {
    @Resource
    private IDDCAuthService authService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponseModel<List<DDCAuth>> AuthList(HttpServletResponse resp){
        authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        List<DDCAuth> list = authService.selectAllAuth();
        return ResponseHelper.buildResponseModel(list);

    }

    @RequestMapping("/delAuth")
    @ResponseBody
    public ResponseModel<String> delAdmin(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id) throws Exception {
        String msg;
        if (id != null) {
            authService = SpringContextBeanService.getBean(IDDCAuthService.class);
            authService.delAuth(id);
            msg = "删除成功";
        }else{
            msg = "数据出错";
        }
        return ResponseHelper.buildResponseModel(msg);
    }

    @RequestMapping("/levelList")
    @ResponseBody
    public ResponseModel<List<List<DDCAuth>>> AuthLevelList(HttpServletResponse resp){
        authService = SpringContextBeanService.getBean(IDDCAuthService.class);
        List<DDCAuth> list = authService.selectAllAuth();
        List<DDCAuth> oneList = new ArrayList<>();
        List<DDCAuth> twoList = new ArrayList<>();
        List<List<DDCAuth>> levelList = new ArrayList<>();

        for(DDCAuth auth : list){
            if(auth.getLevel().equals(1)){
                oneList.add(auth);
            }else if(auth.getLevel().equals(2)){
                twoList.add(auth);
            }
        }
        levelList.add(oneList);
        levelList.add(twoList);

        return ResponseHelper.buildResponseModel(levelList);

    }


    @RequestMapping("/batchDel")
    @ResponseBody
    public void batchDeletes(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="delItems") String delItems) {
        String[] strs = delItems.split(",");
        for (int i = 0; i < strs.length; i++) {
            long oid = Long.parseLong(strs[i]);
            authService.delAuth(oid);
        }
    }
}
