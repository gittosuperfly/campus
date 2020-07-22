package com.cai.campus.app

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.common.push.PushAction
import com.cai.campus.common.push.PushManager
import com.cai.campus.common.utils.JNIUtils
import com.cai.campus.common.utils.Location
import com.cai.campus.common.utils.RSAUtils
import com.mob.MobApplication

class BaseApplication : MobApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this

        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)

        PushManager(PushAction)

        Location.load().location()
    }

    companion object {

        var context: Context? = null

        @JvmStatic
        fun getAppContext(): Context {
            return context as Context
        }
    }


}