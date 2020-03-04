package com.cai.campus.app.push

import android.content.Context
import android.util.Log
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.JPushMessage
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver
import com.cai.campus.app.BaseApplication
import com.orhanobut.logger.Logger

class JPushReceiver : JPushMessageReceiver() {
    /**
     * TODO 连接极光服务器
     */
    override fun onConnected(context: Context, b: Boolean) {
        super.onConnected(context, b)
        Logger.t(TAG).d("极光推送服务器已连接...")
        BaseApplication.ahaha()
    }

    /**
     * TODO 注册极光时的回调
     */
    override fun onRegister(context: Context, s: String) {
        super.onRegister(context, s)
        Log.e(TAG, "onRegister$s")
    }

    /**
     * TODO 注册以及解除注册别名时回调
     */
    override fun onAliasOperatorResult(context: Context, jPushMessage: JPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage)
        Logger.t(TAG).d("Alias set!")
        Log.e(TAG, jPushMessage.toString())
    }

    override fun onTagOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
        super.onTagOperatorResult(context, jPushMessage)
        Logger.t(TAG).d("Tag set!")
        Log.e(TAG, jPushMessage.toString())
    }

    /**
     * TODO 接收到推送下来的通知
     * 可以利用附加字段（notificationMessage.notificationExtras）来区别Notification,指定不同的动作,附加字段是个json字符串
     * 通知（Notification），指在手机的通知栏（状态栏）上会显示的一条通知信息
     */
    override fun onNotifyMessageArrived(
        context: Context,
        notificationMessage: NotificationMessage
    ) {
        super.onNotifyMessageArrived(context, notificationMessage)
        Log.e(TAG, notificationMessage.toString())
    }

    /**
     * TODO 打开了通知
     * notificationMessage.notificationExtras(附加字段)的内容处理代码
     * 比如打开新的Activity， 打开一个网页等..
     */
    override fun onNotifyMessageOpened(context: Context, notificationMessage: NotificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage)
        Log.e(TAG, notificationMessage.notificationExtras)
    }

    /**
     * TODO 接收到推送下来的自定义消息
     * 自定义消息不是通知，默认不会被SDK展示到通知栏上，极光推送仅负责透传给SDK。其内容和展示形式完全由开发者自己定义。
     * 自定义消息主要用于应用的内部业务逻辑和特殊展示需求
     */
    override fun onMessage(context: Context, customMessage: CustomMessage) {
        super.onMessage(context, customMessage)
        Log.e(TAG, customMessage.message)
    }

    companion object {
        private const val TAG = "JPushReceiver"
    }
}