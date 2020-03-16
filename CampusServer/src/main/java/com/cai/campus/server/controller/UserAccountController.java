package com.cai.campus.server.controller;

import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.service.UserAccountService;
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
public class UserAccountController {
    /**
     * 服务对象
     */
    @Resource
    private UserAccountService service;

    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return message
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Response<Null> loginApi(String phone, String password) {
        UserAccount tempUser = service.queryByPhone(phone);
        if (tempUser == null) {
            return Response.get(ResultCode.LOGIN_PHONE_ERROR, "此手机号暂未注册");
        } else if (tempUser.getPassword().equals(password)) {
            return Response.get(ResultCode.SUCCESS, "登录成功");
        } else {
            return Response.get(ResultCode.LOGIN_PASSWORD_ERROR, "密码错误");
        }
    }

    /**
     * 新用户注册
     *
     * @param phone    手机号
     * @param password 密码
     * @return message
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public Response<Null> registerUserApi(String phone, String password) {
        if (service.queryByPhone(phone) == null) {
            UserAccount userAccount = new UserAccount(phone, password);
            service.insert(userAccount);
            return Response.get(ResultCode.SUCCESS, "注册成功");
        } else {
            return Response.get(ResultCode.REGISTER_ERROR, "此手机号已被注册");
        }
    }


    /**
     * 查询用户信息
     *
     * @param type  类型 type {id, phone}
     * @param value 值
     * @return 单条数据
     */
    @RequestMapping(value = "query/{type}", method = RequestMethod.GET)
    public Response<UserAccount> queryUserApi(@PathVariable("type") String type, String value) {
        if (type.equals("id")) {
            return Response.get(ResultCode.SUCCESS, "查询成功", this.service.queryById(Integer.parseInt(value)));
        } else if (type.equals("phone")) {
            return Response.get(ResultCode.SUCCESS, "查询成功", this.service.queryByPhone(value));
        } else {
            return Response.get(ResultCode.QUERY_USER_TYPE_ERROR, "查询类型错误", null);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 修改后的值 { TODO 注意的是，必须传来完整的用户数据，缺失将按null来处理。必须携带id、phone、password }
     * @return message
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public Response<Null> updateUserApi(@RequestBody UserAccount user) {
        if (service.queryById(user.getUid()) != null) {
            service.update(user);
            return Response.get(ResultCode.SUCCESS, "修改成功");
        } else {
            return Response.get(ResultCode.UPDATE_USER_ERROR, "修改失败");
        }
    }


}