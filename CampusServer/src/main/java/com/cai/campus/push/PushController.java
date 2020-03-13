package com.cai.campus.push;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("push")
public class PushController {

    @Resource
    private PushService pushService;

    @GetMapping("test/{alter}")
    public void func(@PathVariable String alter) {

        System.out.println(alter);

        PushData pushData = PushData.builder().alert(alter).build();
        pushService.sendToAll(pushData);
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
