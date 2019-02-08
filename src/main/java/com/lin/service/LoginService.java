package com.lin.service;

import com.lin.common.JsonData;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by linziyu on 2019/1/28.
 */

@Service("LoginService")//坑
@Slf4j
public class LoginService {

    @Resource
    private UserMapper userMapper;


    public JsonData checkNameIsExistOrNot(String login_name){
        User user = userMapper.findByName(login_name);
        if (user != null){
            return new JsonData(300,"存在该用户名");
        }
        return  new JsonData(301,"不存在该用户名");
    }

    public JsonData login(User login_user){
        JsonData jsonData;
        String name = login_user.getName();
        String password = login_user.getPassword();
        User user = userMapper.findByName(name);
        if(user == null){
            return  new JsonData(201,"用户或密码名字错误");
        }
        String user_name = user.getName();
        String user_passwod = user.getPassword();
        if (user_name.equals(name) && user_passwod.equals(password)){
            jsonData = new JsonData(200,"登录成功");
            return  jsonData;
        }
        if(  !user_name.equals(name)  || !user_passwod.equals(password)){
            jsonData = new JsonData(201,"用户或密码名字错误");
            return  jsonData;
        }
        return jsonData = new JsonData(202,"用户不存在");

    }



}
