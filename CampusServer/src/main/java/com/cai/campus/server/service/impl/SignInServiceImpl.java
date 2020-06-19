package com.cai.campus.server.service.impl;

import com.cai.campus.model.ResultCode;
import com.cai.campus.model.WebApiResponse;
import com.cai.campus.push.PushManager;
import com.cai.campus.push.PushRouter;
import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.PushWork;
import com.cai.campus.push.push.PushClient;
import com.cai.campus.push.utils.TargetEnum;
import com.cai.campus.server.dao.*;
import com.cai.campus.server.entity.*;
import com.cai.campus.server.service.GroupService;
import com.cai.campus.server.service.SignInService;
import com.cai.campus.server.service.UserUuidService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (SignIn)表服务实现类
 *
 * @author makejava
 * @since 2020-06-01 16:09:24
 */
@Service("signInService")
public class SignInServiceImpl implements SignInService {

    @Resource
    private SignInDao signInDao;
    @Resource
    private UserAccountDao userDao;
    @Resource
    private SignInRecordDao recordDao;
    @Resource
    private GroupAccountDao groupDao;
    @Resource
    private UserGroupRelationDao relationDao;
    @Resource
    private GroupService groupService;
    @Resource
    private UserUuidService uuidService;

    PushClient pushClient = new PushClient();


    @Override
    public WebApiResponse<Null> releaseSignIn(SignIn newSignIn) {
        //创建签到
        signInDao.insert(newSignIn);
        //添加当前群所有群员到签到记录（默认未签到）
        List<GroupUser> userList = groupService.mQueryGroupAllUser(newSignIn.getGroupId());
        for (GroupUser user : userList) {
            SignInRecord record = new SignInRecord(newSignIn.getSignId(), user.getAccount().getUid());
            record.setIsDone(0);
            record.setIsChangeUUID(0);
            recordDao.insert(record);
        }
        //向所有群成员发布推送信息
        PushWork work = PushManager.CreatePush(null,
                getReleaseString(newSignIn),
                TargetEnum.GROUP.getCode(),
                new String[]{newSignIn.getGroupId().toString()},
                null,
                null);
        try {
            pushClient.sendPush(work);
            return WebApiResponse.get(ResultCode.SUCCESS, "发起签到成功");
        } catch (ApiException e) {
            e.printStackTrace();
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "发起签到失败");
        }
    }

    @Override
    public WebApiResponse<Null> conductSignIn(int signInId, int recordId, String location, String UUID) {
        SignIn signIn = signInDao.queryById(signInId);
        long timestamp = System.currentTimeMillis() / 1000;
        SignInRecord record = recordDao.queryById(recordId);

        if (record.getIsDone() == 1) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "您已经签过到了");
        } else {
            if (timestamp < signIn.getCreateTime()) {
                return WebApiResponse.get(ResultCode.BAD_REQUEST, "签到暂未开始");
            } else if (timestamp > signIn.getEndTime()) {
                return WebApiResponse.get(ResultCode.BAD_REQUEST, "本次签到已结束");
            } else {

                UserUuid userUUID = uuidService.queryByUid(record.getUid());

                System.out.println(userUUID.getUuid());
                System.out.println(UUID);
                System.out.println(!userUUID.getUuid().equals(UUID));

                record.setDoneTime(timestamp);
                record.setIsDone(1);
                record.setLocation(location);
                if (userUUID.getUuid().equals(UUID)){
                    record.setIsChangeUUID(0);
                }else {
                    record.setIsChangeUUID(1);
                    userUUID.setUuid(UUID);
                }
                uuidService.update(userUUID);
                recordDao.update(record);
                return WebApiResponse.get(ResultCode.SUCCESS, "签到成功");
            }
        }
    }

    @Override
    public WebApiResponse<List<SignIn>> queryAllSignIn(int groupId) {
        if (groupDao.queryById(groupId) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "群ID不存在", null);
        }
        return WebApiResponse.get(ResultCode.SUCCESS, signInDao.queryAll(new SignIn(groupId)));
    }

    @Override
    public WebApiResponse<List<GroupSignInRecord>> queryRecordList(int sid) {
        if (signInDao.queryById(sid) == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "签到ID不存在", null);
        }
        List<GroupSignInRecord> data = new ArrayList<>();
        List<SignInRecord> recordList = recordDao.queryAll(new SignInRecord(sid));

        for (SignInRecord record : recordList) {
            data.add(new GroupSignInRecord(record, userDao.queryById(record.getUid()).getName()));
        }
        return WebApiResponse.get(ResultCode.SUCCESS, data);
    }

    @Override
    public WebApiResponse<List<UserSignInDetail>> queryUserAllRecord(int uid) {
        SignInRecord temp = new SignInRecord();
        temp.setUid(uid);
        List<SignInRecord> records = recordDao.queryAll(temp);
        List<UserSignInDetail> detailList = new ArrayList<>();
        for (SignInRecord record : records) {
            UserSignInDetail detail = new UserSignInDetail();
            SignIn signIn = signInDao.queryById(record.getSignId());
            detail.setRecord(record);
            detail.setSignIn(signIn);
            detail.setGroupName(groupDao.queryById(signIn.getGroupId()).getName());
            detailList.add(detail);
        }
        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", detailList);
    }

    private String getReleaseString(SignIn newSignIn) {
        return "群 "
                + groupDao.queryById(newSignIn.getGroupId()).getName()
                + " 发布了签到，立即前往";
    }

    private final double EARTH_RADIUS = 6378.137;//地球半径,单位千米

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
