package com.cai.campus.push;

import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.PushWork;
import com.cai.campus.push.push.PushClient;
import com.cai.campus.push.utils.AndroidNotifyStyleEnum;
import com.cai.campus.push.utils.PlatEnum;
import com.cai.campus.push.utils.PushTypeEnum;
import com.cai.campus.push.utils.TargetEnum;
import com.google.gson.Gson;

import java.util.HashMap;

public class PushManager {

    public static PushWork CreatePush(
            String title,
            String content,
            int target,
            String[] tags,
            String[] phones,
            HashMap<String, String> extra) {
        return new PushWork(PlatEnum.android.getCode(), content, PushTypeEnum.notify.getCode())
                .buildTarget(target, tags, phones, null, null, null)
                .buildAndroid(title, AndroidNotifyStyleEnum.normal.getCode(),
                        null, true, true, true)
                .buildExtra(1, new Gson().toJson(extra));
    }


    public static String pushTest() {


        PushClient client = new PushClient();
        String s = "";
        try {
            String[] tags = {"1"};
            String[] phones = {"15091200140"};
//            HashMap<String, String> extra = new HashMap<>();
//            extra.put("intent", "/app/test");
            PushWork work = CreatePush(null, "手机号推送", TargetEnum.PHONE.getCode(), null, phones, null);

            work.setScheme(PushRouter.TEST);
            client.sendPush(work);

        } catch (ApiException e) {
            e.printStackTrace();
        }
        return s;
    }
}
