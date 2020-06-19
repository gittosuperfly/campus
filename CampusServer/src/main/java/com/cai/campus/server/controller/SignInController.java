package com.cai.campus.server.controller;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.server.entity.GroupSignInRecord;
import com.cai.campus.server.entity.SignIn;
import com.cai.campus.server.entity.SignInRecord;
import com.cai.campus.server.entity.UserSignInDetail;
import com.cai.campus.server.service.SignInService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

/**
 * (SignIn)表控制层
 *
 * @author makejava
 * @since 2020-06-01 16:09:24
 */
@RestController
@RequestMapping("api/signIn")
public class SignInController {
    /**
     * 服务对象
     */
    @Resource
    private SignInService service;

    /**
     * 发布签到
     */
    @RequestMapping(value = "release", method = RequestMethod.POST)
    private WebApiResponse<Null> releaseApi(
            @RequestParam("uid") int uid,
            @RequestParam("groupId") int groupId,
            @RequestParam("createTime") long createTime,
            @RequestParam("endTime") long endTime,
            @RequestParam("location") String location,
            @RequestParam("detail") String detail

    ) {
        SignIn signIn = new SignIn();
        signIn.setUid(uid);
        signIn.setGroupId(groupId);
        signIn.setCreateTime(createTime);
        signIn.setEndTime(endTime);
        signIn.setLocation(location);
        signIn.setDetail(detail);
        return this.service.releaseSignIn(signIn);
    }

    /**
     * 查询群内所有签到
     */
    @RequestMapping(value = "queryAllSignIn", method = RequestMethod.POST)
    private WebApiResponse<List<SignIn>> queryAllSignInApi(
            @RequestParam("groupId") int groupId
    ) {
        return this.service.queryAllSignIn(groupId);
    }

    /**
     * 查询sid签到的签到记录
     */
    @RequestMapping(value = "queryRecordList", method = RequestMethod.POST)
    private WebApiResponse<List<GroupSignInRecord>> querySignInRecordListApi(
            @RequestParam("sid") int sid
    ) {
        return this.service.queryRecordList(sid);
    }

    /**
     * 查询用户uid的签到记录
     */
    @RequestMapping(value = "queryUserAllRecordList", method = RequestMethod.POST)
    private WebApiResponse<List<UserSignInDetail>> queryUserAllRecordApi(
            @RequestParam("userId") int uid
    ) {
        return this.service.queryUserAllRecord(uid);
    }

    /**
     * 签到
     */
    @RequestMapping(value = "goSignIn", method = RequestMethod.POST)
    private WebApiResponse<Null> signInApi(
            @RequestParam("signInId") int signInId,
            @RequestParam("recordId") int recordId,
            @RequestParam("location") String location,
            @RequestParam("UUID") String UUID
    ) {
        return this.service.conductSignIn(signInId, recordId, location, UUID);
    }


}