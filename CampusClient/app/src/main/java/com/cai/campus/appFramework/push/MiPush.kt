package com.cai.campus.appFramework.push

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import android.util.Log
import com.cai.campus.collocation.AppConfig
import com.xiaomi.channel.commonutils.logger.LoggerInterface
import com.xiaomi.mipush.sdk.MiPushClient

object MiPush {
    fun init(app: Application) {
        //初始化push推送服务
        if (shouldInit(app)) {
            MiPushClient.registerPush(app, AppConfig.MI_PUSH_APP_ID, AppConfig.MI_PUSH_APP_KEY)
        }
        //打开Log
        val newLogger: LoggerInterface = object : LoggerInterface {
            override fun setTag(tag: String) { // ignore
            }

            override fun log(content: String, t: Throwable) {
                Log.d(AppConfig.appTag, content, t)
            }

            override fun log(content: String) {
                Log.d(AppConfig.appTag, content)
            }
        }
        com.xiaomi.mipush.sdk.Logger.setLogger(app, newLogger)
    }

    private fun shouldInit(app: Application): Boolean {
        val am = app.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = app.applicationInfo.processName
        val myPid = Process.myPid()
        for (info in processInfos) {
            if (info.pid == myPid && mainProcessName == info.processName) {
                return true
            }
        }
        return false
    }
}