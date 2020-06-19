package com.cai.campus.server.service.impl;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.dao.*;
import com.cai.campus.server.entity.*;
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

    @Resource
    private SignInRecordDao recordDao;

    @Resource
    private SignInDao signInDao;


    @Override
    public GroupAccount getGroupById(Integer gid) {
        return groupDao.queryById(gid);
    }

    @Override
    public WebApiResponse<Null> createGroup(String groupName, int creatorUid) {
        GroupAccount group = new GroupAccount();
        group.setName(groupName);
        group.setCreateTime(System.currentTimeMillis());
        groupDao.insert(group);
        UserGroupRelation relation = new UserGroupRelation();
        relation.setGroupId(group.getGroupId());
        relation.setUid(creatorUid);
        relation.setStatus(UserGroupRelation.STATUS.LEADER);
        relationDao.insert(relation);
        return WebApiResponse.get(ResultCode.SUCCESS, "建群成功");
    }

    @Override
    public GroupAccount updateGroup(GroupAccount group) {
        groupDao.update(group);
        return group;
    }

    @Override
    public WebApiResponse<Null> deleteGroup(Integer groupId) {
        if (groupDao.queryById(groupId) != null) {
            SignIn signIn = new SignIn();
            signIn.setGroupId(groupId);
            List<SignIn> signInList = signInDao.queryAll(signIn);
            for (SignIn item : signInList) {
                recordDao.deleteBySignInId(item.getSignId());
                signInDao.deleteById(item.getSignId());
            }
            relationDao.deleteByGroupId(groupId);
            groupDao.deleteById(groupId);
            return WebApiResponse.get(ResultCode.SUCCESS, "删除成功");
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "删除失败，groupId不存在");
        }
    }


    @Override
    public WebApiResponse<Null> userAddGroup(Integer uid, Integer gid) {
        if (groupDao.queryById(gid) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在");
        }
        if (relationDao.queryUserIsEmptyGroup(uid, gid) == null) {
            UserGroupRelation relation = new UserGroupRelation();
            relation.setUid(uid);
            relation.setGroupId(gid);
            relation.setStatus(0);
            relationDao.insert(relation);
            return WebApiResponse.get(ResultCode.SUCCESS, "加群成功");
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "该用户已在此群中");
        }
    }

    @Override
    public WebApiResponse<Null> userQuitGroup(Integer uid, Integer gid) {
        if (groupDao.queryById(gid) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在");
        }
        UserGroupRelation relation = relationDao.queryUserIsEmptyGroup(uid, gid);
        if (relation != null) {
            relationDao.deleteById(relation.getId());
            return WebApiResponse.get(ResultCode.SUCCESS, "退群成功");
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "该用户不在此群中");
        }
    }

    @Override
    public WebApiResponse<List<GroupAccount>> queryUserAllGroup(Integer uid) {
        UserGroupRelation relation = new UserGroupRelation();
        relation.setUid(uid);
        List<GroupAccount> list = new ArrayList<>();
        List<UserGroupRelation> relations = relationDao.queryAll(relation);
        for (UserGroupRelation userGroupRelation : relations) {
            list.add(groupDao.queryById(userGroupRelation.getGroupId()));
        }
        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", list);
    }

    @Override
    public WebApiResponse<List<GroupUser>> queryGroupAllUser(Integer groupId) {
        if (groupDao.queryById(groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在", null);
        }

        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", mQueryGroupAllUser(groupId));
    }

    @Override
    public List<GroupUser> mQueryGroupAllUser(Integer groupId) {
        UserGroupRelation relation = new UserGroupRelation();
        relation.setGroupId(groupId);
        List<GroupUser> resultList = new ArrayList<>();
        List<UserGroupRelation> relations = relationDao.queryAll(relation);

        for (UserGroupRelation userGroupRelation : relations) {
            resultList.add(new GroupUser(userGroupRelation.getStatus(),
                    userDao.queryById(userGroupRelation.getUid())));
        }
        return resultList;
    }

    @Override
    public WebApiResponse<Null> setGroupAdmin(int groupId, int userId, int value) {
        if (groupDao.queryById(groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在", null);
        } else if (relationDao.queryUserIsEmptyGroup(userId, groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "该成员不在群中", null);
        } else {
            UserGroupRelation relation = relationDao.queryUserIsEmptyGroup(userId, groupId);
            relation.setStatus(value);
            relationDao.update(relation);
            return WebApiResponse.get(ResultCode.SUCCESS, "设置成功", null);
        }
    }

    @Override
    public WebApiResponse<Null> transferGroup(int groupId, int oldUserId, int newUserId) {
        if (groupDao.queryById(groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在", null);
        } else if (relationDao.queryUserIsEmptyGroup(newUserId, groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "该成员不在群中", null);
        } else {
            UserGroupRelation oldRelation = relationDao.queryUserIsEmptyGroup(oldUserId, groupId);
            if (oldRelation.getStatus() != 2) {
                return WebApiResponse.get(ResultCode.BAD_REQUEST, "您不是群主，无法转让该群", null);
            } else {
                oldRelation.setStatus(0);
                UserGroupRelation newRelation = relationDao.queryUserIsEmptyGroup(newUserId, groupId);
                newRelation.setStatus(2);

                relationDao.update(oldRelation);
                relationDao.update(newRelation);

                return WebApiResponse.get(ResultCode.SUCCESS, "群转让成功", null);
            }
        }
    }


}
