package com.ddc.server.controller;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCMember;
import com.ddc.server.mapper.DDCMemberMapper;
import com.ddc.server.service.IDDCMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/member")
@Controller
@Slf4j
public class MemberController {
    @Resource
    private IDDCMemberService memberService;
    @Resource
    private DDCMemberMapper memberMapper;

    @RequestMapping("/add")
    public String jumpAdd(Model model) throws Exception {
        return "member-add";
    }
    @RequestMapping("/show")
    public String jumpShow(Model model, @RequestParam(value = "id",required = false) String id) throws Exception {
        return "member_show";
    }

    @RequestMapping("/edit")
    public String jumpEdit(Model model) throws Exception {
        return "member_edit";
    }

    @RequestMapping("/addAction")
    @ResponseBody
    public void insertAdd(HttpServletRequest request, @RequestParam(value = "username",required = false) String username, @RequestParam(value = "sex",required = false) Integer sex,
                          @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
                          @RequestParam(value = "city",required = false) String city,@RequestParam(value = "beizhu",required = false) String beizhu,Model model) throws Exception {
        DDCMember member = new DDCMember(username, sex, mobile, email,city,beizhu);
        //PasswordUtils.entryptPassword(member);
        memberMapper.insert(member);
    }

    @RequestMapping("/memberList")
    @ResponseBody
    public ResponseModel<List<DDCMember>> getMemberList(HttpServletResponse response){
        List<DDCMember> memberList=memberService.selectMemberList();
        return  ResponseHelper.buildResponseModel(memberList);
    }

    @RequestMapping("/memberSearch")
    @ResponseBody
    public ResponseModel<List<DDCMember>> getMemberSearch(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "search",required = false) String search){
        List<DDCMember> memberList=memberMapper.selectByName(search);
        return  ResponseHelper.buildResponseModel(memberList);
    }

    @RequestMapping("/deleteMember")
    @ResponseBody
    public ResponseModel<String> memberDelete(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "id",required = false) Long id){
        memberMapper.deleteById(id);
        return ResponseHelper.buildResponseModel("ok");
    }

    @RequestMapping("/memberShow")
    @ResponseBody
    public ResponseModel<DDCMember> getMemberShow(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "id",required = false) String id){
        DDCMember member=memberMapper.selectMemberById(id);
        System.out.println(id);
        return  ResponseHelper.buildResponseModel(member);
    }

    @RequestMapping("/memberEdit")
    @ResponseBody
    public ResponseModel<DDCMember> memberEdit(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "id",required = false) String id){
        DDCMember member=memberMapper.selectMemberById(id);
        System.out.println(id);
        return  ResponseHelper.buildResponseModel(member);
    }

    @RequestMapping("/updAction")
    @ResponseBody
    public boolean memberUpd(HttpServletRequest request, @RequestParam(value = "id",required = false) Long id, @RequestParam(value = "username",required = false) String username, @RequestParam(value = "sex",required = false) Integer sex,
                                              @RequestParam(value = "mobile",required = false) String mobile, @RequestParam(value = "email",required = false) String email,
                                              @RequestParam(value = "city",required = false) String city,@RequestParam(value = "beizhu",required = false) String beizhu,Model model){
        //PasswordUtils.entryptPassword(member);
        System.out.println(id);
        memberMapper.updateMember(id,username,sex,mobile,email,city,beizhu);
        return true;
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println(subject.getPrincipal()+"退出登录！");
        return "login";
    }

}