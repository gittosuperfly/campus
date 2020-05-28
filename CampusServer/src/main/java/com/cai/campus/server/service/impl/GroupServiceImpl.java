package com.cai.campus.server.service.impl;

import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.dao.GroupAccountDao;
import com.cai.campus.server.dao.UserAccountDao;
import com.cai.campus.server.dao.UserGroupRelationDao;
import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.entity.GroupUser;
import com.cai.campus.server.entity.UserGroupRelation;
import com.cai.campus.server.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupAccountDao groupDao;

    @Resource
    private UserGroupRelationDao relationDao;

    @Resource
    private UserAccountDao userDao;

    @Override
    public GroupAccount getGroupById(Integer gid) {
        return groupDao.queryById(gid);
    }

    @Override
    public Response<Null> createGroup(String groupName, int creatorUid) {
        GroupAccount group = new GroupAccount();
        group.setName(groupName);
        group.setCreateTime(System.currentTimeMillis());
        groupDao.insert(group);
        UserGroupRelation relation = new UserGroupRelation();
        relation.setGroupId(group.getGroupId());
        relation.setUid(creatorUid);
        relation.setStatus(UserGroupRelation.STATUS.LEADER);
        relationDao.insert(relation);
        return Response.get(ResultCode.SUCCESS, "建群成功");
    }

    @Override
    public GroupAccount updateGroup(GroupAccount group) {
        groupDao.update(group);
        return group;
    }

    @Override
    public Response<Null> deleteGroup(Integer groupId) {
        if (groupDao.queryById(groupId) != null) {
            relationDao.deleteByGroupId(groupId);
            groupDao.deleteById(groupId);
            return Response.get(ResultCode.SUCCESS, "删除成功");
        } else {
            return Response.get(ResultCode.BAD_REQUEST, "删除失败，groupId不存在");
        }
    }


    @Override
    public Response<Null> userAddGroup(Integer uid, Integer gid) {
        if (groupDao.queryById(gid) == null) {
            return Response.get(ResultCode.BAD_REQUEST, "群ID不存在");
        }
        if (relationDao.queryUserIsEmptyGroup(uid, gid) == null) {
            UserGroupRelation relation = new UserGroupRelation();
            relation.setUid(uid);
            relation.setGroupId(gid);
            relation.setStatus(0);
            relationDao.insert(relation);
            return Response.get(ResultCode.SUCCESS, "加群成功");
        } else {
            return Response.get(ResultCode.BAD_REQUEST, "该用户已在此群中");
        }
    }

    @Override
    public Response<Null> userQuitGroup(Integer uid, Integer gid) {
        if (groupDao.queryById(gid) == null) {
            return Response.get(ResultCode.BAD_REQUEST, "群ID不存在");
        }
        UserGroupRelation relation = relationDao.queryUserIsEmptyGroup(uid, gid);
        if (relation != null) {
            relationDao.deleteById(relation.getId());
            return Response.get(ResultCode.SUCCESS, "退群成功");
        } else {
            return Response.get(ResultCode.BAD_REQUEST, "该用户不在此群中");
        }
    }

    @Override
    public Response<List<GroupAccount>> queryUserAllGroup(Integer uid) {
        UserGroupRelation relation = new UserGroupRelation();
        relation.setUid(uid);
        List<GroupAccount> list = new ArrayList<>();
        List<UserGroupRelation> relations = relationDao.queryAll(relation);
        for (UserGroupRelation userGroupRelation : relations) {
            list.add(groupDao.queryById(userGroupRelation.getGroupId()));
        }
        return Response.get(ResultCode.SUCCESS, "查询成功", list);
    }

    @Override
    public Response<List<GroupUser>> queryGroupAllUser(Integer groupId) {
        if (groupDao.queryById(groupId) == null) {
            return Response.get(ResultCode.BAD_REQUEST, "群ID不存在", null);
        }
        UserGroupRelation relation = new UserGroupRelation();
        relation.setGroupId(groupId);
        List<GroupUser> resultList = new ArrayList<>();
        List<UserGroupRelation> relations = relationDao.queryAll(relation);

        for (UserGroupRelation userGroupRelation : relations) {
            resultList.add(new GroupUser(userGroupRelation.getStatus(),
                    userDao.queryById(userGroupRelation.getUid())));
        }
        return Response.get(ResultCode.SUCCESS, "查询成功", resultList);
    }
}
