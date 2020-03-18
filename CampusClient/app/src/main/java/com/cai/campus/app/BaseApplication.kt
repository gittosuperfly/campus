package com.cai.campus.app

import android.app.Application
import com.cai.campus.common.push.PushAction
import com.cai.campus.common.push.PushManager
import com.mob.MobApplication

class BaseApplication : MobApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this
        PushManager(PushAction)
    }

    companion object {
        var context: Application? = null
    }
}