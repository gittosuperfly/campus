package com.cai.campus.push;


import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Null;

@RestController
public class PushController {

    @RequestMapping(value = "push", method = RequestMethod.GET)
    public String updateUserApi() {
        return PushManager.pushAll();
    }


}
