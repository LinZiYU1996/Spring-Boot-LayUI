package com.lin.mapper;

import com.lin.entity.User;

import java.util.List;

/**
 * Created by linziyu on 2019/1/27.
 */
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(int id);

    User findByName(String name);

    List<User> getAllUsers();

    void saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
    
}
