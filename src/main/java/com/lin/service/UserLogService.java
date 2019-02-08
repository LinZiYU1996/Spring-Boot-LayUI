package com.lin.service;

import com.lin.entity.User;
import com.lin.entity.UserLog;
import com.lin.mapper.UserLogMapper;
import com.lin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by linziyu on 2019/2/3.
 */

@Service("UserLogService")
@Slf4j
public class UserLogService {

    @Resource
    private UserLogMapper userLogMapper;

    @Resource
    private UserMapper userMapper;

    public void saveUserLog(Integer old_user_id,Integer new_user_id,String current_user_name,String old_user_name
    ,String old_user_pwd){
        User old_user = userMapper.findById(old_user_id);
        User new_user = userMapper.findById(new_user_id);
        log.info("{}","old_user"+old_user.getName());
        log.info("{}","new_user"+new_user);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date=sdf.format(now);
        UserLog userLog = new UserLog(old_user_name,old_user_pwd,new_user.getName(),
                new_user.getPassword(),current_user_name,date,old_user.getId());
        userLogMapper.saveUserLog(userLog);




    }

    public List<UserLog> getAllUserLogs(){
        return  userLogMapper.getAllUserLogs();
    }


}
