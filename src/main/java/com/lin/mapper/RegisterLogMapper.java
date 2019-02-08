package com.lin.mapper;

import com.lin.entity.RegisterLog;

import java.util.List;

public interface RegisterLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegisterLog record);

    int insertSelective(RegisterLog record);

    RegisterLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegisterLog record);

    int updateByPrimaryKey(RegisterLog record);

    void saveRegisterLog(RegisterLog registerLog);

    List<RegisterLog> getAllRegisterLog();

}