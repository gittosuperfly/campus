package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (SignIn)实体类
 *
 * @author makejava
 * @since 2020-06-01 16:09:24
 */
public class SignIn implements Serializable {
    private static final long serialVersionUID = -62269484241224203L;

    private Integer signId;

    private Integer groupId;

    private Long createTime;

    private Long endTime;

    private Integer uid;

    private String location;

    private String detail;

    public SignIn() {
    }

    public SignIn(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}