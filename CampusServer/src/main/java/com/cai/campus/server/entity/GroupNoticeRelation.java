package com.cai.campus.server.entity;

import java.io.Serializable;

/**
 * (GroupNoticeRelation)实体类
 *
 * @author makejava
 * @since 2020-06-14 16:08:41
 */
public class GroupNoticeRelation implements Serializable {
    private static final long serialVersionUID = 143679206799192037L;
    
    private Integer id;
    
    private Integer noticeId;
    
    private Integer uid;

    public GroupNoticeRelation() {
    }

    public GroupNoticeRelation(Integer noticeId, Integer uid) {
        this.noticeId = noticeId;
        this.uid = uid;
    }

    public GroupNoticeRelation(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}