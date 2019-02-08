package com.lin.entity;

import lombok.AllArgsConstructor;


/*
用户信息更改记录日志
 */
@AllArgsConstructor
public class UserLog {
    private Integer id;

    private String oldUserName;

    private String newUserName;

    private String oldUserPwd;

    private String newUserPwd;

    private String operatorName;

    private String operatorTime;

    private Integer targetId;

    public UserLog (String oldUserName,String oldUserPwd,String newUserName,
                    String newUserPwd,String operatorName,String operatorTime,Integer targetId){
                this.oldUserName = oldUserName;
                this.oldUserPwd = oldUserPwd;
                this.newUserName = newUserName;
                this.newUserPwd = newUserPwd;
                this.operatorName = operatorName;
                this.operatorTime = operatorTime;
                this.targetId = targetId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldUserName() {
        return oldUserName;
    }

    public void setOldUserName(String oldUserName) {
        this.oldUserName = oldUserName == null ? null : oldUserName.trim();
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName == null ? null : newUserName.trim();
    }

    public String getOldUserPwd() {
        return oldUserPwd;
    }

    public void setOldUserPwd(String oldUserPwd) {
        this.oldUserPwd = oldUserPwd == null ? null : oldUserPwd.trim();
    }

    public String getNewUserPwd() {
        return newUserPwd;
    }

    public void setNewUserPwd(String newUserPwd) {
        this.newUserPwd = newUserPwd == null ? null : newUserPwd.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime == null ? null : operatorTime.trim();
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }
}