package com.cai.campus.service.impl;

import com.cai.campus.domain.app.ResponseData;
import com.cai.campus.service.IFaceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("FaceService")
public class FaceServiceImpl implements IFaceService {
    @Override
    public ResponseData testFunction() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test1", "111");
        map.put("test2", "112");
        map.put("test3", "113");
        return new ResponseData("success", 200, map);
    }
}
