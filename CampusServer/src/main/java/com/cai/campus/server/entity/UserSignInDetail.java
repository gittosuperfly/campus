package com.cai.campus.server.entity;

public class UserSignInDetail {
    private SignIn signIn;
    private SignInRecord record;
    private String groupName;

    public SignIn getSignIn() {
        return signIn;
    }

    public void setSignIn(SignIn signIn) {
        this.signIn = signIn;
    }

    public SignInRecord getRecord() {
        return record;
    }

    public void setRecord(SignInRecord record) {
        this.record = record;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
