package com.cai.campus.app

import android.app.Application
import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.cai.campus.collocation.LoggerCollocation
import com.orhanobut.logger.Logger


class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        context = this
        Logger.addLogAdapter(LoggerCollocation.getLoggerAdapter())
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)


    }



    companion object {
        var context: Application? = null
        fun getContext(): Context {
            return context!!
        }

        fun ahaha(){
            val set = HashSet<String>()
            set += "tag1"
            JPushInterface.setTags(context, 1, set)
        }
    }


}