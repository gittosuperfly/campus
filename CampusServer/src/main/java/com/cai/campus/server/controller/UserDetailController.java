package com.cai.campus.server.controller;

import com.cai.campus.server.entity.UserDetail;
import com.cai.campus.server.service.UserDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserDetail)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
@RestController
@RequestMapping("userDetail")
public class UserDetailController {
    /**
     * 服务对象
     */
    @Resource
    private UserDetailService userDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserDetail selectOne(Integer id) {
        return this.userDetailService.queryById(id);
    }

}