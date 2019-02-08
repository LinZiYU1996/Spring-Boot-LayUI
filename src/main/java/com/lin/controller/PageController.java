package com.lin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.LoginLog;
import com.lin.entity.RegisterLog;
import com.lin.entity.User;
import com.lin.entity.UserLog;
import com.lin.mapper.UserMapper;
import com.lin.service.LogService;
import com.lin.service.UserLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linziyu on 2019/2/7.
 */

@Controller
@RequestMapping(value = "/u")
public class PageController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LogService logService;

    @Resource
    private UserLogService userLogService;


    //用户资料分页
    @RequestMapping(value = "/getAllUser")
    @ResponseBody
    public Map<String,Object> page(int page, int limit){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(page,limit);
        //startPage后紧跟的这个查询就是分页查询
        List<User> users = userMapper.getAllUsers();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<User>(users, 5);
        Map<String,Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", pageInfo.getPages()*5);
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", users);
        //返回给前端
        return data;

    }

    //登录日志分页
    @RequestMapping(value = "/getAllLoginLog")
    @ResponseBody
    public Map<String,Object> loginLogPage(int page,int limit){
        PageHelper.startPage(page,limit);
        List<LoginLog> loginLogs = logService.getAllLoginLog();
        PageInfo pageInfo = new PageInfo<LoginLog>(loginLogs, 5);
        Map<String,Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", pageInfo.getPages()*5);
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", loginLogs);
        return  data;
    }

    //注册日志分页
    @RequestMapping(value = "/getAllRegisterLog")
    @ResponseBody
    public Map<String,Object> registerLogPage(int page,int limit){
        PageHelper.startPage(page,limit);
        List<RegisterLog> registerLogs = logService.getAllRegisterLog();
        PageInfo pageInfo = new PageInfo<RegisterLog>(registerLogs, 5);
        Map<String,Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", pageInfo.getPages()*5);
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", registerLogs);
        return data;
    }

    //用户资料更改日志分页
    @RequestMapping(value = "/getAllUserLog")
    @ResponseBody
    public Map<String,Object> userLogPage(int page,int limit){
        PageHelper.startPage(page,limit);
        List<UserLog> userLogs = userLogService.getAllUserLogs();
        PageInfo pageInfo = new PageInfo<UserLog>(userLogs, 5);
        Map<String,Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        data.put("count", pageInfo.getPages()*5);
        //将分页后的数据返回（每页要显示的数据）
        data.put("data", userLogs);
        return data;

    }



}
