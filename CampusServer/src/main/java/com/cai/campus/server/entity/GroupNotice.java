package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupNotice)实体类
 *
 * @author makejava
 * @since 2020-06-14 15:11:16
 */
public class GroupNotice implements Serializable {
    private static final long serialVersionUID = 784679973965363400L;
    
    private Integer id;
    
    private Integer groupId;
    
    private Integer uid;
    
    private Long releaseTime;
    
    private String notice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}