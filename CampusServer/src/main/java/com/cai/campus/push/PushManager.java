package com.cai.campus.push;

import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.PushWork;
import com.cai.campus.push.utils.AndroidNotifyStyleEnum;
import com.cai.campus.push.utils.PlatEnum;
import com.cai.campus.push.utils.PushTypeEnum;
import com.cai.campus.push.utils.TargetEnum;
import com.google.gson.Gson;

import java.util.HashMap;

public class PushManager {

    static PushWork CreatePush(
            String title,
            String content,
            String[] tags,
            String[] phones,
            String[] ids,
            HashMap<String, String> extra) {
        return new PushWork(PlatEnum.android.getCode(), content, PushTypeEnum.notify.getCode())
                .buildTarget(TargetEnum._1.getCode(), tags, phones, ids, null, null)
                .buildAndroid(title, AndroidNotifyStyleEnum.normal.getCode(),
                        null, true, true, true)
                .buildExtra(1, new Gson().toJson(extra), 1);
    }

    static String pushAll() {

        MobPushClient client = new MobPushClient();
        String s = "";
        try {
            s = client.push(PushManager.CreatePush(
                    "贾碧莹是猪", "啊哈哈哈哈哈哈~~~", null, null, null, null));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return s;
    }
}
