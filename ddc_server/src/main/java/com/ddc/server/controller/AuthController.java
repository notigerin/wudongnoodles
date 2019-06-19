package com.ddc.server.controller;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.service.IDDCAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
        List<DDCAuth> list = authService.selectAllAuth();
        return ResponseHelper.buildResponseModel(list);
    }
}
