package com.cai.campus.server.controller;

import com.cai.campus.server.entity.GroupNotice;
import com.cai.campus.server.service.GroupNoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GroupNotice)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 22:38:10
 */
@RestController
@RequestMapping("groupNotice")
public class GroupNoticeController {
    /**
     * 服务对象
     */
    @Resource
    private GroupNoticeService groupNoticeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GroupNotice selectOne(Integer id) {
        return this.groupNoticeService.queryById(id);
    }

}