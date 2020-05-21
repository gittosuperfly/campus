package com.cai.campus.server.controller;


import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.server.service.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/group")
public class GroupController {

    @Resource
    private GroupService service;

    @RequestMapping("test")
    private Response<String> test() {
        return Response.get(ResultCode.SUCCESS, "测试成功", this.service.test());
    }
}
