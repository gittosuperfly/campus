package com.cai.campus.collocation

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Logger相关的配置
 */
object LoggerCollocation {
    fun getLoggerAdapter(): AndroidLogAdapter {
        return if (AppConfig.isDebug) AndroidLogAdapter(
            PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) //（可选）是否显示线程信息。 默认值为true
                .methodCount(1) //（可选）要显示的方法行数。 默认2
                .tag(AppConfig.appTag)
                .build()
        ) else {
            object : AndroidLogAdapter() {
                override fun isLoggable(priority: Int, tag: String?): Boolean {
                    return BuildConfig.DEBUG
                }
            }
        }
    }
}