package com.lin.service;

import com.lin.common.JsonData;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by linziyu on 2019/1/30.
 */

@Service("RegisterService")
@Slf4j
public class RegisterService {

    @Resource
    private  UserMapper userMapper;


    public JsonData checkNameIsExistOrNot(String register_name){
        User user = userMapper.findByName(register_name);
        if (user != null){
            return  new JsonData(303,"用户名早就存在了");
        }
        return  new JsonData(304,"用户名可以使用");
    }

    public JsonData saveUser(User user,String name){
//        log.info("{}",name);
        userMapper.saveUser(user);
        return  new JsonData(200,"注册成功");
    }


}
