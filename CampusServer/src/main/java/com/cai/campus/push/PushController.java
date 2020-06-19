package com.cai.campus.push;


import com.cai.campus.model.WebApiResponse;
import com.cai.campus.model.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RestController
public class PushController {

    @RequestMapping(value = "push")
    public WebApiResponse<Null> updateUserApi() {
        return WebApiResponse.get(ResultCode.SUCCESS, PushManager.pushTest());
    }
}