package com.cai.campus.server.entity;

public class GroupSignInRecord {
    private SignInRecord record;
    private String userName;

    public GroupSignInRecord(SignInRecord record, String userName) {
        this.record = record;
        this.userName = userName;
    }

    public SignInRecord getRecord() {
        return record;
    }

    public void setRecord(SignInRecord record) {
        this.record = record;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
