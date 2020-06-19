package com.cai.campus.server.entity;

public class GroupMessage {
    private GroupNotice notice;
    private String groupName;
    private String createUserName;

    public GroupMessage() {
    }

    public GroupMessage(GroupNotice notice, String groupName, String createUserName) {
        this.notice = notice;
        this.groupName = groupName;
        this.createUserName = createUserName;
    }

    public GroupNotice getNotice() {
        return notice;
    }

    public void setNotice(GroupNotice notice) {
        this.notice = notice;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
