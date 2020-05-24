package com.cai.campus.server.service;

import com.cai.campus.model.Response;
import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.entity.GroupUser;
import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.entity.UserGroupRelation;
import org.apache.catalina.User;

import javax.validation.constraints.Null;
import java.util.List;

public interface GroupService {

    /**
     * 通过id查询群组
     */
    GroupAccount getGroupById(Integer gid);

    /**
     * 建群
     */
    GroupAccount createGroup(GroupAccount group);

    /**
     * 修改群信息
     */
    GroupAccount updateGroup(GroupAccount group);

    /**
     * 解散群
     */
    boolean deleteGroup(Integer groupId);


    /**
     * 加群
     */
    Response<Null> userAddGroup(Integer uid, Integer gid);

    /**
     * 退群
     */
    Response<Null> userQuitGroup(Integer uid, Integer gid);

    /**
     * 查询一个用户加的所有群的信息
     */
    Response<List<GroupAccount>> queryUserAllGroup(Integer uid);

    /**
     * 查询一个群的所有群成员信息
     */
    Response<List<GroupUser>> queryGroupAllUser(Integer groupId);

}