package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupAccount)实体类
 *
 * @author makejava
 * @since 2020-04-28 23:57:08
 */
public class GroupAccount implements Serializable {
    private static final long serialVersionUID = 778001288152303472L;
    
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