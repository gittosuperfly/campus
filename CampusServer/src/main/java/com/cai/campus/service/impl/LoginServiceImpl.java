package com.cai.campus.service.impl;

import com.cai.campus.domain.app.ResponseData;
import com.cai.campus.repository.LoginRepository;
import com.cai.campus.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginRepository repo;

    @Override
    public ResponseData login(String id , String password) {
        if (id.equals("123") && password.equals("asd")){
            return new ResponseData("登录成功",1,null);
        }
        return new ResponseData("登录失败",-1,null);
    }

    @Override
    public ResponseData register() {
        return null;
    }

    @Override
    public ResponseData change() {
        return null;
    }
}
