package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (SignInRecord)实体类
 *
 * @author makejava
 * @since 2020-06-07 18:35:36
 */
public class SignInRecord implements Serializable {
    private static final long serialVersionUID = 638352643038151770L;

    private Integer id;

    private Integer signId;

    private Integer uid;

    private Integer isDone;

    private Long doneTime;

    private String location;

    private Integer isChangeUUID;

    public SignInRecord(Integer signId, Integer uid) {
        this.signId = signId;
        this.uid = uid;
    }

    public SignInRecord(Integer signId) {
        this.signId = signId;
    }

    public SignInRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsDone() {
        return isDone;
    }

    public void setIsDone(Integer isDone) {
        this.isDone = isDone;
    }

    public Long getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Long doneTime) {
        this.doneTime = doneTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIsChangeUUID() {
        return isChangeUUID;
    }

    public void setIsChangeUUID(Integer isChangeUUID) {
        this.isChangeUUID = isChangeUUID;
    }
}