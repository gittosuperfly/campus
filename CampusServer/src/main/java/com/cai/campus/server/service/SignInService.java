package com.cai.campus.server.service;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.server.entity.GroupSignInRecord;
import com.cai.campus.server.entity.SignIn;
import com.cai.campus.server.entity.SignInRecord;
import com.cai.campus.server.entity.UserSignInDetail;

import javax.validation.constraints.Null;
import javax.xml.ws.Response;
import java.util.List;

/**
 * 签到服务接口
 *
 * @author caiyufei
 * @since 2020-06-01 16:09:24
 */
public interface SignInService {
    //TODO 群内发布签到
    WebApiResponse<Null> releaseSignIn(SignIn newSignIn);

    //TODO 用户UserId进行签到
    WebApiResponse<Null> conductSignIn(int signInId, int recordId, String location,String UUID);

    //TODO 查看群groupId内的所有签到<List<SignIn>>
    WebApiResponse<List<SignIn>> queryAllSignIn(int groupId);

    //TODO 查看签到sid的所有签到记录<List<SignInRecord>>
    WebApiResponse<List<GroupSignInRecord>> queryRecordList(int sid);

    //TODO 查看用户User的所有签到记录
    WebApiResponse<List<UserSignInDetail>> queryUserAllRecord(int uid);

    //TODO 查看用户在某个群的已签到列表
}