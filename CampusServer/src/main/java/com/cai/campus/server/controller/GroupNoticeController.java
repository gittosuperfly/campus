package com.cai.campus.server.controller;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.server.entity.GroupMessage;
import com.cai.campus.server.entity.GroupNotice;
import com.cai.campus.server.service.GroupNoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * (GroupNotice)表控制层
 *
 * @author makejava
 * @since 2020-06-14 15:11:16
 */
@RestController
@RequestMapping("api/notice")
public class GroupNoticeController {
    /**
     * 服务对象
     */
    @Resource
    private GroupNoticeService service;

    /**
     * 1. 发布群公告
     * 2. 查询当前群的所有群公告
     * 3. 查询当前用户的所有未删除群公告
     * 4. 单用户删除群公告
     */

    @RequestMapping(value = "release", method = RequestMethod.POST)
    public WebApiResponse<Null> releaseApi(
            @RequestParam("uid") int uid,
            @RequestParam("groupId") int groupId,
            @RequestParam("releaseTime") long createTime,
            @RequestParam("detail") String detail
    ) {
        GroupNotice notice = new GroupNotice();
        notice.setUid(uid);
        notice.setGroupId(groupId);
        notice.setReleaseTime(createTime);
        notice.setNotice(detail);
        return this.service.insert(notice);
    }

    @RequestMapping(value = "queryByGroup", method = RequestMethod.POST)
    public WebApiResponse<List<GroupNotice>> queryByGroupApi(
            @RequestParam("groupId") int groupId
    ) {
        return this.service.queryByGroup(groupId);
    }

    @RequestMapping(value = "queryByUser", method = RequestMethod.POST)
    public WebApiResponse<List<GroupMessage>> queryByUserApi(
            @RequestParam("userId") int uid
    ) {
        return this.service.queryByUser(uid);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public WebApiResponse<Null> deleteApi(
            @RequestParam("userId") int userId,
            @RequestParam("noticeId") int noticeId

    ) {
        return this.service.deleteById(userId,noticeId);
    }

}