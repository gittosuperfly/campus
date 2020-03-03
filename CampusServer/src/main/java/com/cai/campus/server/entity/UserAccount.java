package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (UserAccount)实体类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 353658853937410730L;
    
    private Integer uid;
    
    private String phone;
    
    private String password;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}