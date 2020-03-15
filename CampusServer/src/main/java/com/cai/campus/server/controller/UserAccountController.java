package com.cai.campus.server.controller;

import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private UserAccountService userAccountService;

    @PostMapping("register")
    public Response<Object> registerUserApi(String phone, String password) {
        if (userAccountService.queryByPhone(phone) == null) {
            UserAccount userAccount = new UserAccount(phone, password);
            userAccountService.insert(userAccount);
            return Response.ok("注册成功");
        } else {
            return Response.error(400, "此手机号码已注册");
        }
    }


    /**
     * 查询用户信息
     *
     * @param type  类型 type {id, phone}
     * @param value 值
     * @return 单条数据
     */
    @PostMapping("query/{type}")
    public Response<UserAccount> queryUserApi(@PathVariable("type") String type, String value) {
        if (type.equals("id")) {
            return Response.ok(this.userAccountService.queryById(Integer.parseInt(value)));
        } else if (type.equals("phone")) {
            return Response.ok(this.userAccountService.queryByPhone(value));
        } else {
            return Response.error(ResultCode.BAD_REQUEST, "查询类型错误");
        }
    }

}