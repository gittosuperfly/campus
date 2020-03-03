package com.cai.campus.server.controller;

import com.cai.campus.server.entity.UserGroupRelation;
import com.cai.campus.server.service.UserGroupRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserGroupRelation)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
@RestController
@RequestMapping("userGroupRelation")
public class UserGroupRelationController {
    /**
     * 服务对象
     */
    @Resource
    private UserGroupRelationService userGroupRelationService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserGroupRelation selectOne(Integer id) {
        return this.userGroupRelationService.queryById(id);
    }

}