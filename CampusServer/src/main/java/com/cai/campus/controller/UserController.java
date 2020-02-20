package com.cai.campus.controller;

import com.cai.campus.entity.User;
import com.cai.campus.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-02-19 22:14:14
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 通过电话号码查询单条数据
     *
     * @param phoneNumber 电话号码
     * @return 单条数据
     */
    @GetMapping("query/phoneNumber")
    public User queryUserByPhoneNumberApi(String phoneNumber) {
        return this.userService.queryByPhoneNumber(phoneNumber);
    }

    @GetMapping("addUser")
    public User addUserApi(
            @Param("realName") String realName,
            @Param("phoneNumber") String phoneNumber,
            @Param("password") String password) {
        User user = new User();
        user.setRealname(realName);
        user.setPhonenumber(phoneNumber);
        user.setPassword(password);
        System.out.println(user.toString());
        return this.userService.insert(user);
    }

}