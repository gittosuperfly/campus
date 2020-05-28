package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupAccount)实体类
 *
 * @author makejava
 * @since 2020-05-12 15:40:31
 */
public class GroupAccount implements Serializable {
    private static final long serialVersionUID = 947758087054179715L;
    
    private Integer groupId;
    
    private Long createTime;
    
    private String name;
    
    private String logo;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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