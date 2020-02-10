package com.cai.campus.controller;

import com.cai.campus.domain.app.ResponseData;
import com.cai.campus.service.ILoginService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * 账号登录相关接口
 * 登录 login
 * 注册 register
 * 修改密码 changePassword
 */

@RestController
public class LoginController {

    final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseData loginApi(@PathParam("id") String id , @PathParam("password") String password) {
        return loginService.login(id,password);
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public ResponseData registerApi() {
        return loginService.register();
    }

    @RequestMapping(value = "/changePassword")
    @ResponseBody
    public ResponseData changeApi() {
        return loginService.change();
    }
}
