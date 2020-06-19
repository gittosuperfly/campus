package com.cai.campus.server.service;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.entity.GroupUser;

import javax.validation.constraints.Null;
import java.util.List;

public interface GroupService {

    /**
     * 通过id查询群组
     */
    GroupAccount getGroupById(Integer gid);

    /**
     * 建群
     *
     * @return
     */
    WebApiResponse<Null> createGroup(String groupName, int creatorUid);

    /**
     * 修改群信息
     */
    GroupAccount updateGroup(GroupAccount group);

    /**
     * 解散群
     */
    WebApiResponse<Null> deleteGroup(Integer groupId);


    /**
     * 加群
     */
    WebApiResponse<Null> userAddGroup(Integer uid, Integer gid);

    /**
     * 退群
     */
    WebApiResponse<Null> userQuitGroup(Integer uid, Integer gid);

    /**
     * 查询一个用户加的所有群的信息
     */
    WebApiResponse<List<GroupAccount>> queryUserAllGroup(Integer uid);

    /**
     * 查询一个群的所有群成员信息
     */
    WebApiResponse<List<GroupUser>> queryGroupAllUser(Integer groupId);

    List<GroupUser> mQueryGroupAllUser(Integer groupId);

    /**
     * 修改管理员信息
     *
     * @param status 值
     */
    WebApiResponse<Null> setGroupAdmin(int groupId, int userId, int status);

    WebApiResponse<Null> transferGroup(int groupId, int oldUserId, int newUserId);

}