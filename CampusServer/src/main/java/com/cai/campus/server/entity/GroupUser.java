package com.cai.campus.server.entity;

public class GroupUser {
    private Integer status;
    private UserAccount userInfo;

    public GroupUser(Integer status, UserAccount userInfo) {
        this.status = status;
        this.userInfo = userInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer state) {
        this.status = state;
    }

    public UserAccount getAccount() {
        return userInfo;
    }

    public void setAccount(UserAccount account) {
        this.userInfo = account;
    }
}