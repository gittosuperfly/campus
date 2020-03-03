package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupNotice)实体类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 22:38:10
 */
public class GroupNotice implements Serializable {
    private static final long serialVersionUID = -22124632031354957L;
    
    private Integer id;
    
    private Integer groupId;
    
    private Integer uid;
    
    private Integer releaseTime;
    
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

    public Integer getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Integer releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}