package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (UserUuid)实体类
 *
 * @author makejava
 * @since 2020-06-11 18:54:50
 */
public class UserUuid implements Serializable {
    private static final long serialVersionUID = -97214438717529879L;
    
    private Integer id;
    
    private String uuid;
    
    private Integer uid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserUuid{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}