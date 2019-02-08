package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
注册日志实体类
 */
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLog {
    private Integer id;

    private String registerUserName;

    private String registerTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterUserName() {
        return registerUserName;
    }

    public void setRegisterUserName(String registerUserName) {
        this.registerUserName = registerUserName == null ? null : registerUserName.trim();
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

    public RegisterLog (String registerUserName,String registerTime){
            this.registerTime = registerTime;
            this.registerUserName = registerUserName;
    }


}