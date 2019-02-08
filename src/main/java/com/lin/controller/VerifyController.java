package com.lin.controller;

import com.lin.common.JsonData;
import com.lin.service.RegisterService;
import com.lin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by linziyu on 2019/2/7.
 */

@Controller
@RequestMapping(value = "/u")
public class VerifyController {


    @Resource
    private UserService userService;

    @Resource
    private RegisterService registerService;

    //更改用户信息时的名字的校验
    @RequestMapping(value = "/checkName")
    @ResponseBody
    public JsonData checkName(String name, HttpSession session){
        String old_name = (String) session.getAttribute("oldName");
        return  userService.checkName(old_name,name);
    }

    //登录时名字的校验
    @RequestMapping(value = "/checkRegisterName")
    @ResponseBody
    public JsonData checkRegisterIsExistOrNot(String name){
        return registerService.checkNameIsExistOrNot(name);
    }




}
