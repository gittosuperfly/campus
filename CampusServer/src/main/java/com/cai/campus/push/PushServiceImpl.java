package com.cai.campus.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "push")
public class PushServiceImpl implements PushService {

    @Value("${push.appKey}")
    private String appKey;

    @Value("${push.secret}")
    private String secret;

    private JPushClient client;

    @PostConstruct
    public void initPushClient() {
        client = new JPushClient(secret, appKey);
    }

    private static final int MAX_SIZE = 1000;

    @Override
    public boolean sendToAll(PushData pushData) {
        return sendMessage(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(
                        pushData.getAlert(),
                        pushData.getTitle(),
                        pushData.getExtras()))
                .build());
    }

    @Override
    public boolean sendToTag(PushData pushData, String... tags) {
        return sendMessage(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.android(
                        pushData.getAlert(),
                        pushData.getTitle(),
                        pushData.getExtras()))
                .build());
    }

    @Override
    public boolean sendToAlias(PushData pushData, String... aliases) {
        return sendMessage(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(aliases))
                .setNotification(Notification.android(
                        pushData.getAlert(),
                        pushData.getTitle(),
                        pushData.getExtras()))
                .build());
    }

    @Override
    public boolean sendToRegistrationID(PushData pushData, String... ids) {

        ids = checkRegisterIds(ids);

        ArrayList<Boolean> isSuccesses = new ArrayList<>();

        while (ids.length > MAX_SIZE) {
            isSuccesses.add(sendMessage(PushPayload.newBuilder()
                    .setPlatform(Platform.android())
                    .setAudience(Audience.registrationId(Arrays.copyOfRange(ids, 0, MAX_SIZE)))
                    .setNotification(Notification.android(
                            pushData.getAlert(),
                            pushData.getTitle(),
                            pushData.getExtras()))
                    .build()));
            ids = Arrays.copyOfRange(ids, MAX_SIZE, ids.length);
        }

        isSuccesses.add(sendMessage(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(ids))
                .setNotification(Notification.android(
                        pushData.getAlert(),
                        pushData.getTitle(),
                        pushData.getExtras()))
                .build()));

        for (boolean isSuccess : isSuccesses) {
            if (!isSuccess) return false;
        }
        return true;
    }

    @Override
    public boolean sendMessage(PushPayload payload) {
        PushResult result = null;
        try {
            result = client.sendPush(payload);
            client.close();
            log.debug("send success!");
        } catch (APIConnectionException | APIRequestException e) {
            log.error(e.getMessage());
        }
        return result != null && result.isResultOK();
    }


    /**
     * 剔除无效id
     *
     * @param ids id列表
     * @return 检查完毕后的id列表
     */
    public String[] checkRegisterIds(String... ids) {
        List<String> list = new ArrayList<>(ids.length);
        for (String id : ids) {
            if (id != null && !"".equals(id.trim())) {
                list.add(id);
            }
        }
        return list.toArray(new String[0]);
    }

}
