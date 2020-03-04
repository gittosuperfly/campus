package com.cai.campus.push;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("push")
public class PushController {

    @Resource
    private PushService pushService;

    @GetMapping("test")
    public void func() {
        PushData pushData = PushData.builder().alert("123456789").build();
        pushService.sendToTag(pushData, "tag3", "tag2");
    }

    @GetMapping("")
    public void pushApi() {
        HashMap<String, String> map = new HashMap<>();
        map.put("color", "blue");
        map.put("money", "￥30");
        map.put("size", "20");
        PushData pushData = PushData.builder().alert("好好学习天天向上").title("快点买睡衣").extras(map).build();
        pushService.sendToAll(pushData);
    }
}
