package com.cai.campus.push;


import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RestController
public class PushController {

    @RequestMapping(value = "push", method = RequestMethod.GET)
    public Response<Null> updateUserApi() {
        return Response.get(ResultCode.SUCCESS, PushManager.pushAll());
    }


}
