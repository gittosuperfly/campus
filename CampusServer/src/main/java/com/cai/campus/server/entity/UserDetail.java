package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (UserDetail)实体类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 564228140399251882L;
    
    private Integer uid;
    
    private String name;
    
    private Integer sex;
    
    private String logo;
    
    private Integer birthday;
    
    private String introduction;
    
    private String email;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}