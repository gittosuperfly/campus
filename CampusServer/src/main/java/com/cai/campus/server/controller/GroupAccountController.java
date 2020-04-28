package com.cai.campus.server.controller;

import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.service.GroupAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GroupAccount)表控制层
 *
 * @author makejava
 * @since 2020-04-28 23:57:08
 */
@RestController
@RequestMapping("groupAccount")
public class GroupAccountController {
    /**
     * 服务对象
     */
    @Resource
    private GroupAccountService groupAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GroupAccount selectOne(Integer id) {
        return this.groupAccountService.queryById(id);
    }

}