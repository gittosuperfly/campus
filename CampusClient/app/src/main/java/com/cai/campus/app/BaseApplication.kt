package com.cai.campus.app

import android.app.Application
import android.content.Context
import com.mob.MobSDK
import com.mob.pushsdk.MobPush


class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        context = this
        initMobSDK()
    }

    private fun initMobSDK() {
        MobSDK.init(this)
        MobSDK.submitPolicyGrantResult(true, null)
        MobPush.initMobPush()
    }

    companion object {
        var context: Application? = null
    }
}