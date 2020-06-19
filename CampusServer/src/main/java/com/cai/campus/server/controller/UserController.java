package com.cai.campus.server.controller;

import com.cai.campus.model.WebApiResponse;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Null;

/**
 * (UserAccount)表控制层
 *
 * @author caiyufei
 * @since 2020-03-14 17:42:08
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService service;

    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public WebApiResponse<Null> loginApi(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {
        UserAccount tempUser = service.queryByPhone(phone);
        if (tempUser == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "此手机号暂未注册");
        } else if (tempUser.getPassword().equals(password)) {
            return WebApiResponse.get(ResultCode.SUCCESS, "登录成功");
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "密码错误");
        }
    }

    /**
     * 新用户注册
     *
     * @param phone    手机号
     * @param password 密码
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public WebApiResponse<Null> registerUserApi(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("UUID") String uuid) {

            return service.insert(phone,password,uuid);
    }

    /**
     * 修改密码
     *
     * @param phone    手机号
     * @param password 新密码
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public WebApiResponse<Null> resetPasswordApi(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {
        UserAccount user = service.queryByPhone(phone);
        if (user == null) {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "手机号不存在");
        } else {
            user.setPassword(password);
            service.update(user);
            return WebApiResponse.get(ResultCode.SUCCESS, "修改成功");
        }
    }


    /**
     * 查询用户信息
     *
     * @param type  类型 type {id, phone}
     * @param value 值
     */
    @RequestMapping(value = "query/{type}", method = RequestMethod.POST)
    public WebApiResponse<UserAccount> queryUserApi(
            @PathVariable("type") String type,
            @RequestParam("value") String value) {
        if (type.equals("id")) {
            return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", this.service.queryById(Integer.parseInt(value)));
        } else if (type.equals("phone")) {
            return WebApiResponse.get(ResultCode.SUCCESS, "查询成功", this.service.queryByPhone(value));
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "查询类型错误", null);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 修改后的值 { TODO 必须传来完整的用户数据，缺失将按null来处理。必须携带id、phone、password }
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public WebApiResponse<Null> updateUserApi(@RequestBody UserAccount user) {
        if (service.queryById(user.getUid()) != null) {
            service.update(user);
            return WebApiResponse.get(ResultCode.SUCCESS, "修改成功");
        } else {
            return WebApiResponse.get(ResultCode.BAD_REQUEST, "修改失败");
        }
    }


}