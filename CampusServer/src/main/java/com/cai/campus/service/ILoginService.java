package com.cai.campus.service;

import com.cai.campus.domain.app.ResponseData;

public interface ILoginService {
    ResponseData login(String id , String password);
    ResponseData register();
    ResponseData change();
}
