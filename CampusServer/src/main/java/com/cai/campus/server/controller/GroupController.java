package com.cai.campus.server.controller;


import com.cai.campus.model.Response;
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
    private Response<GroupAccount> createGroupApi(
            @RequestParam("createTime") Integer time,
            @RequestParam("groupName") String name
    ) {
        GroupAccount account = new GroupAccount();
        account.setCreateTime(time);
        account.setName(name);
        return Response.get(ResultCode.SUCCESS, "创建成功", this.service.createGroup(account));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    private Response<Null> deleteGroupApi(
            @RequestParam("groupId") Integer gid) {
        if (this.service.deleteGroup(gid)) {
            return Response.get(ResultCode.SUCCESS, "删除成功");
        } else {
            return Response.get(ResultCode.BAD_REQUEST, "删除失败，groupId不存在");
        }
    }

    @RequestMapping(value = "queryById", method = RequestMethod.POST)
    private Response<GroupAccount> queryGroupApi(
            @RequestParam("groupId") Integer gid) {
        return Response.get(ResultCode.SUCCESS, "查询成功", this.service.getGroupById(gid));
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    private Response<GroupAccount> updateGroupApi(
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
            Response.get(ResultCode.BAD_REQUEST, "参数type错误", null);
        }
        return Response.get(ResultCode.SUCCESS, "修改成功", this.service.updateGroup(group));
    }

    //TODO ==================================================================
    //TODO ============================  分割线  ============================
    //TODO ==================================================================

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    private Response<Null> userAddGroupApi(
            @RequestParam("userId") Integer uid,
            @RequestParam("groupId") Integer gid
    ) {
        return service.userAddGroup(uid, gid);
    }

    @RequestMapping(value = "quitGroup", method = RequestMethod.POST)
    private Response<Null> userQuitGroupApi(
            @RequestParam("userId") Integer uid,
            @RequestParam("groupId") Integer gid
    ) {
        return service.userQuitGroup(uid, gid);
    }

    @RequestMapping(value = "queryUserAllGroup", method = RequestMethod.POST)
    private Response<List<GroupAccount>> queryUserAllGroupApi(
            @RequestParam("userId") Integer uid
    ) {
        return service.queryUserAllGroup(uid);
    }

    @RequestMapping(value = "queryGroupAllUser", method = RequestMethod.POST)
    private Response<List<GroupUser>> queryGroupAllUserApi(
            @RequestParam("groupId") Integer groupId
    ) {
        return service.queryGroupAllUser(groupId);
    }
}
