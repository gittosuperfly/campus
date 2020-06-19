package com.cai.campus.server.controller;


import com.cai.campus.model.WebApiResponse;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.entity.GroupUser;
import com.cai.campus.server.service.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {

    @Resource
    private GroupService service;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    private WebApiResponse<Null> createGroupApi(
            @RequestParam("groupName") String name,
            @RequestParam("creatorUid") Integer uid
    ) {
        return this.service.createGroup(name, uid);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    private WebApiResponse<Null> deleteGroupApi(
            @RequestParam("groupId") Integer gid) {
        return this.service.deleteGroup(gid);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.POST)
    private WebApiResponse<GroupAccount> queryGroupApi(
            @RequestParam("groupId") Integer gid) {
        return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", this.service.getGroupById(gid));
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    private WebApiResponse<GroupAccount> updateGroupApi(
            @RequestParam("groupId") Integer gid,
            @RequestParam("updateType") String type,
            @RequestParam("updateValue") String value
    ) {
        GroupAccount group = new GroupAccount();
        group.setGroupId(gid);
        if (type.equals("name")) {
            group.setName(value);
        } else if (type.equals("logo")) {
            group.setLogo(value);
        } else {
            WebApiResponse.get(ResultCode.BAD_REQUEST, "参数type错误", null);
        }
        return WebApiResponse.get(ResultCode.SUCCESS, "修改成功", this.service.updateGroup(group));
    }

    //TODO ==================================================================
    //TODO ============================  分割线  ============================
    //TODO ==================================================================

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    private WebApiResponse<Null> userAddGroupApi(
            @RequestParam("userId") Integer uid,
            @RequestParam("groupId") Integer gid
    ) {
        return service.userAddGroup(uid, gid);
    }

    @RequestMapping(value = "quitGroup", method = RequestMethod.POST)
    private WebApiResponse<Null> userQuitGroupApi(
            @RequestParam("userId") Integer uid,
            @RequestParam("groupId") Integer gid
    ) {
        return service.userQuitGroup(uid, gid);
    }

    @RequestMapping(value = "queryUserAllGroup", method = RequestMethod.POST)
    private WebApiResponse<List<GroupAccount>> queryUserAllGroupApi(
            @RequestParam("userId") Integer uid
    ) {
        return service.queryUserAllGroup(uid);
    }

    @RequestMapping(value = "queryGroupAllUser", method = RequestMethod.POST)
    private WebApiResponse<List<GroupUser>> queryGroupAllUserApi(
            @RequestParam("groupId") Integer groupId
    ) {
        return service.queryGroupAllUser(groupId);
    }

    @RequestMapping(value = "setGroupAdmin", method = RequestMethod.POST)
    private WebApiResponse<Null> setGroupAdminApi(
            @RequestParam("groupId") Integer groupId,
            @RequestParam("userId") Integer userId,
            @RequestParam("status") Integer status
    ) {
        return service.setGroupAdmin(groupId, userId, status);
    }

    @RequestMapping(value = "transferGroup", method = RequestMethod.POST)
    private WebApiResponse<Null> transferGroupApi(
            @RequestParam("groupId") Integer groupId,
            @RequestParam("oldUserId") Integer oldUserId,
            @RequestParam("newUserId") Integer newUserId
    ) {
        return service.transferGroup(groupId, oldUserId, newUserId);
    }
}
