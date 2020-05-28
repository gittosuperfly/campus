package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (UserGroupRelation)实体类
 *
 * @author makejava
 * @since 2020-05-12 15:41:24
 */
public class UserGroupRelation implements Serializable {
    private static final long serialVersionUID = 672804131671702066L;
    
    private Integer id;
    
    private Integer uid;
    
    private Integer groupId;
    
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static final class STATUS{
        public static int MEMBER = 0;
        public static int ADMIN = 1;
        public static int LEADER = 2;
    }

}