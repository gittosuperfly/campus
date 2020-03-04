package com.cai.campus.push;

import cn.jpush.api.push.model.PushPayload;
import org.springframework.stereotype.Service;

//TODO 目前只支持 Android 平台 暂无 IOS 平台推送支持计划

@Service
public interface PushService {

    /**
     * 向所有人推送
     *
     * @return 推送是否成功
     */
    boolean sendToAll(PushData pushData);

    /**
     * 向指定Tag推送
     *
     * @return 推送是否成功
     */
    boolean sendToTag(PushData pushData, String... tags);

    /**
     * 向指定Alias推送
     *
     * @return 推送是否成功
     */
    boolean sendToAlias(PushData pushData, String... aliases);

    /**
     * 向指定ID推送
     *
     * @return 推送是否成功
     */
    boolean sendToRegistrationID(PushData pushData, String... ids);

    /**
     * 自定义 PushPayload
     *
     * @return 推送是否成功
     */
    boolean sendMessage(PushPayload payload);

}
