package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by linziyu on 2019/1/27.
 */

/*
用户实体类
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private int id;
    private String name;
    private String password;




}
