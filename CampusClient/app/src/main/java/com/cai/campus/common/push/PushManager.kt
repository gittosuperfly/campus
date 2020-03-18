package com.cai.campus.common.push

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.cai.campus.app.BaseApplication
import com.mob.pushsdk.MobPush
import com.mob.pushsdk.MobPushCustomMessage
import com.mob.pushsdk.MobPushNotifyMessage
import com.mob.pushsdk.MobPushReceiver

class PushManager(action: PushAction) {
    init {
        pushEventDispatch()
    }

    private fun pushEventDispatch() {
        MobPush.addPushReceiver(object : MobPushReceiver {
            //接收自定义消息(透传)
            override fun onCustomMessageReceive(
                context: Context,
                message: MobPushCustomMessage
            ) {
                Log.d("caicai", Looper.getMainLooper().thread.name + "   " + message.content)
            }

            //接收通知消息
            override fun onNotifyMessageReceive(
                context: Context,
                message: MobPushNotifyMessage
            ) {
                Log.d("caicai", Looper.getMainLooper().thread.name + "   " + message.content)

            }

            //接收通知消息被点击事件
            override fun onNotifyMessageOpenedReceive(
                context: Context,
                message: MobPushNotifyMessage
            ) {

            }

            //接收tags的增改删查操作
            override fun onTagsCallback(
                context: Context,
                tags: Array<String>,
                operation: Int,
                errorCode: Int
            ) {

            }

            //接收alias的增改删查操作
            override fun onAliasCallback(
                context: Context,
                alias: String,
                operation: Int,
                errorCode: Int
            ) {

            }
        })
    }

    companion object {
        //以手机号作为别名，用于1v1推送 重复设置会覆盖之前的
        fun setPhone(phone: String) {
            MobPush.setAlias(phone)
        }

        //以群组作为Tag，用于群组内推送
        fun addGroup(groups: Array<String>) {
            MobPush.addTags(groups)
        }

        //退出群组时删除标签
        fun removeGroup(groups: Array<String>) {
            MobPush.deleteTags(groups)
        }
    }
}

