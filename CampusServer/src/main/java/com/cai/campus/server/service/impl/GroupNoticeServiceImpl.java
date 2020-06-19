package com.cai.campus.server.service.impl;

import com.cai.campus.model.ResultCode;
import com.cai.campus.model.WebApiResponse;
import com.cai.campus.push.PushManager;
import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.PushWork;
import com.cai.campus.push.push.PushClient;
import com.cai.campus.push.utils.TargetEnum;
import com.cai.campus.server.dao.GroupAccountDao;
import com.cai.campus.server.dao.GroupNoticeRelationDao;
import com.cai.campus.server.dao.UserAccountDao;
import com.cai.campus.server.entity.GroupMessage;
import com.cai.campus.server.entity.GroupNotice;
import com.cai.campus.server.dao.GroupNoticeDao;
import com.cai.campus.server.entity.GroupNoticeRelation;
import com.cai.campus.server.entity.GroupUser;
import com.cai.campus.server.service.GroupNoticeService;
import com.cai.campus.server.service.GroupService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * (GroupNotice)表服务实现类
 *
 * @author makejava
 * @since 2020-06-14 15:11:16
 */
@Service("groupNoticeService")
public class GroupNoticeServiceImpl implements GroupNoticeService {

    @Resource
    private GroupAccountDao groupDao;

    @Resource
    private UserAccountDao userDao;


    @Resource
    private GroupNoticeDao groupNoticeDao;
    @Resource
    private GroupNoticeRelationDao relationDao;

    @Resource
    private GroupService groupService;

    PushClient pushClient = new PushClient();

    /**
     * 新增群公告
     *
     * @param groupNotice 实例对象
     * @return 实例对象
     */
    @Override
    public WebApiResponse<Null> insert(GroupNotice groupNotice) {
        groupNoticeDao.insert(groupNotice);
        List<GroupUser> userList = groupService.mQueryGroupAllUser(groupNotice.getGroupId());
        for (GroupUser user : userList) {
            GroupNoticeRelation relation = new GroupNoticeRelation(groupNotice.getId(), user.getAccount().getUid());
            System.out.println(new Gson().toJson(relation));
            relationDao.insert(relation);
        }
        //向所有群成员发布推送信息
        PushWork work = PushManager.CreatePush(groupDao.queryById(groupNotice.getGroupId()).getName(),
                groupNotice.getNotice(),
                TargetEnum.GROUP.getCode(),
                new String[]{groupNotice.getGroupId().toString()},
                null,
                null);
        try {
            pushClient.sendPush(work);
            return WebApiResponse.get(ResultCode.SUCCESS, "信息发布成功");
        } catch (ApiException e) {
            e.printStackTrace();
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "信息发布失败");
        }
    }

    @Override
    public WebApiResponse<List<GroupNotice>> queryByGroup(int groupId) {
        GroupNotice notice = new GroupNotice();
        notice.setGroupId(groupId);
        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", groupNoticeDao.queryAll(notice));
    }

    @Override
    public WebApiResponse<List<GroupMessage>> queryByUser(int userId) {
        GroupNoticeRelation relation = new GroupNoticeRelation();
        relation.setUid(userId);
        List<GroupNoticeRelation> relationList = relationDao.queryAll(relation);
        List<GroupMessage> messageList = new ArrayList<>();
        for (GroupNoticeRelation data : relationList) {
            GroupNotice notice = groupNoticeDao.queryById(data.getNoticeId());
            messageList.add(
                    new GroupMessage(
                            notice,
                            groupDao.queryById(notice.getGroupId()).getName(),
                            userDao.queryById(notice.getUid()).getName()
                    )
            );
        }
        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", messageList);
    }

    @Override
    public WebApiResponse<Null> deleteById(Integer userId, Integer noticeId) {
        GroupNoticeRelation relation = new GroupNoticeRelation(noticeId, userId);
        List<GroupNoticeRelation> relationList = relationDao.queryAll(relation);
        relationDao.deleteById(relationList.get(0).getId());
        return WebApiResponse.get(ResultCode.SUCCESS, "删除成功");
    }
}