package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupAccount)实体类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:30
 */
public class GroupAccount implements Serializable {
    private static final long serialVersionUID = -21896306803086584L;
    
    private Integer groupId;
    
    private Integer createTime;
    
    private String name;
    
    private String logo;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}