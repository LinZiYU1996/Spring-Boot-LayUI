package com.lin.mapper;

import com.lin.entity.UserLog;

import java.util.List;

public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    void saveUserLog(UserLog userLog);

    List<UserLog> getAllUserLogs();
}