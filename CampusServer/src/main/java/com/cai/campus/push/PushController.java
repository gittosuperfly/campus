package com.cai.campus.push;


import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RestController
public class PushController {

    @RequestMapping(value = "push")
    public Response<Null> updateUserApi() {
        return Response.get(ResultCode.SUCCESS, PushManager.pushAll());
    }


    @RequestMapping(value = "test")
    public Response<Test> testApi(int value) {
        if (value < 10) {
            return Response.get(ResultCode.SUCCESS, "ok", new Test("message!!", value));
        } else {
            return Response.get(ResultCode.BAD_REQUEST, "错误测试", null);
        }
    }

}

class Test {
    public Test(String message, int code) {
        this.message = message;
        this.code = code;
    }

    String message;
    int code;
}
