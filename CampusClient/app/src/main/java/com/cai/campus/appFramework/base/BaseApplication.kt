package com.cai.campus.appFramework.base

import android.app.Application
import com.cai.campus.appFramework.push.MiPush
import com.cai.campus.collocation.LoggerCollocation
import com.orhanobut.logger.Logger


class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(LoggerCollocation.getLoggerAdapter())
        MiPush.init(this)
    }

}