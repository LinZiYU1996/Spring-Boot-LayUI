package com.lin.mapper;


import com.lin.entity.LoginLog;

import java.util.List;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    void saveLoginLog (LoginLog loginLog);

    List<LoginLog> getAllLoginLog();
    LoginLog selectOne(Integer id);
}