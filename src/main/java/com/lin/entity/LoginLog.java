package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/*
登录日志实体类
 */
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog implements Serializable{
    private Integer id;

    private String loginUserName;

    private Date loginTime;

    private Date logoutTime;

    public LoginLog(String loginUserName,Date loginTime,Date logoutTime){
            this.loginTime = loginTime;
            this.loginUserName = loginUserName;
            this.logoutTime = logoutTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName == null ? null : loginUserName.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}