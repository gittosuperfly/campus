package com.cai.campus.server.entity;

public class GroupUser {
    private Integer state;
    private UserAccount account;

    public GroupUser(Integer state, UserAccount account) {
        this.state = state;
        this.account = account;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
}