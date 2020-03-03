package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (UserGroupRelation)实体类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public class UserGroupRelation implements Serializable {
    private static final long serialVersionUID = -60476352115007237L;
    
    private Integer uid;
    
    private Integer groupId;
    
    private Integer status;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}