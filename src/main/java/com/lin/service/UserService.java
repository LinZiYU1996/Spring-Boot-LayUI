package com.lin.service;

import com.lin.common.JsonData;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by linziyu on 2019/2/1.
 */

@Service("UserService")
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;


    public JsonData updateUser(User user){
        log.info("{}",user.getId(),user.getName(),user.getPassword());
        int result = userMapper.updateUser(user);
        if (result > 0) {
            return  new JsonData(200,"更改成功");
        }
        return  new JsonData(502,"更改失败");
    }

    public JsonData deleteUser(User user){
        int result = userMapper.deleteUser(user.getId());
        if (result > 0){
            return  new JsonData(200,"删除成功");
        }
        return  new JsonData(602,"删除失败");
    }

    public JsonData checkName(String old_name,String new_name){
        if (old_name.equals(new_name)){
            return  new  JsonData(101,"一样");
        }
        User user = userMapper.findByName(new_name);
        if (user != null) {
            return  new JsonData(102,"重复");
        }
        return  new JsonData(200,"OK");
    }


}
