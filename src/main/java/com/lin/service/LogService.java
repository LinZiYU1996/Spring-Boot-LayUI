package com.lin.service;

import com.lin.entity.LoginLog;
import com.lin.entity.RegisterLog;
import com.lin.mapper.LoginLogMapper;
import com.lin.mapper.RegisterLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by linziyu on 2019/2/3.
 */

@Service("LogService")
@Slf4j
public class LogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Resource
    private RegisterLogMapper registerLogMapper;

    public void saveLoginLog(String user_name) throws ParseException {
        Date now=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String tablename=sdf.format(now);
        Date login_dat = sdf.parse(tablename);
        LoginLog loginLog = new LoginLog(user_name,login_dat,login_dat);
        loginLogMapper.saveLoginLog(loginLog);

    }

    public List<LoginLog> getAllLoginLog(){
        List<LoginLog> logs = loginLogMapper.getAllLoginLog();
        return logs;
    }

    public LoginLog sec(Integer id){
        return loginLogMapper.selectOne(id);
    }


    public void saveRegisterLog(String register_name){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date=sdf.format(now);
        RegisterLog registerLog = new RegisterLog(register_name,date);
        registerLogMapper.saveRegisterLog(registerLog);


    }

    public List<RegisterLog> getAllRegisterLog(){
        return registerLogMapper.getAllRegisterLog();
    }


}
