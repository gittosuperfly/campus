package com.cai.campus.server.controller;

import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserAccount)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
@RestController
@RequestMapping("userAccount")
public class UserAccountController {
    /**
     * 服务对象
     */
    @Resource
    private UserAccountService userAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserAccount selectOne(Integer id) {
        return this.userAccountService.queryById(id);
    }

}