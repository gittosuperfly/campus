package com.cai.campus.server.service;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.server.entity.GroupMessage;
import com.cai.campus.server.entity.GroupNotice;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * (GroupNotice)表服务接口
 *
 * @author makejava
 * @since 2020-06-14 15:11:16
 */
public interface GroupNoticeService {

    /**
     * 1. 发布群公告
     * 2. 查询当前群的所有群公告
     * 3. 查询当前用户的所有未删除群公告
     * 4. 单用户删除群公告
     */

    WebApiResponse<Null> insert(GroupNotice notice);

    WebApiResponse<List<GroupNotice>> queryByGroup(int groupId);

    WebApiResponse<List<GroupMessage>> queryByUser(int userId);

    WebApiResponse<Null> deleteById(Integer userId,Integer noticeId);

}