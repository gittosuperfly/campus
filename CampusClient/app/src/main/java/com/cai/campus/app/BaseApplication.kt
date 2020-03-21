package com.cai.campus.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.common.push.PushAction
import com.cai.campus.common.push.PushManager
import com.mob.MobApplication

class BaseApplication : MobApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this

        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)

        PushManager(PushAction)
    }

    companion object {

        var context: Context? = null

        fun getAppContext(): Context {
            return context as Context
        }
    }


}