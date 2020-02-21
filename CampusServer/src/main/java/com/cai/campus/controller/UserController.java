package com.cai.campus.controller;

import com.cai.campus.entity.User;
import com.cai.campus.model.Response;
import com.cai.campus.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author 蔡宇飞
 * @since 2020-02-21 17:05:05
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
     * 注册用户
     *
     * @param phone    手机号
     * @param password 密码
     * @return json
     */
    @GetMapping("register")
    public Response<User> registerApi(@Param("phone") String phone, @Param("password") String password) {
        return this.userService.register(phone, password);
    }

    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return json
     */
    @GetMapping("login")
    public Response<Object> loginApi(@Param("phone") String phone, @Param("password") String password) {
        return this.userService.login(phone, password);
    }

    /**
     * 按类型查询用户
     *
     * @param type  查询类型 {id, phone}
     * @param value 查询值
     * @return Json
     */
    @GetMapping("query/{type}")
    public Response<User> queryApi(@PathVariable("type") String type, String value) {
        return this.userService.query(type, value);
    }

    /**
     * 修改用户信息
     *
     * @param uid      uid确定用户
     * @param password 要修改的密码
     * @param name     要修改的名称
     * @return json
     */
    @GetMapping("update")
    public Response<User> updateApi(@Param("uid") int uid, @Param("password") String password, @Param("name") String name) {
        return this.userService.update(uid, password, name);
    }

}