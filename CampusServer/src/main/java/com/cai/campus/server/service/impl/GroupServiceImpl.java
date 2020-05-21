package com.cai.campus.server.service.impl;

import com.cai.campus.server.service.GroupService;
import org.springframework.stereotype.Service;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {
    @Override
    public String test() {
        return "群组Service测试";
    }
}
