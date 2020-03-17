package com.cai.campus.common.sms

import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.OnSendMessageHandler
import cn.smssdk.SMSSDK

class SMSManager(sendOk: () -> Unit, checkOk: () -> Unit) {
    init {
        smsEventDispatch()
    }

    private fun smsEventDispatch() {
        val eventHandler: EventHandler = object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    when (event) {
                        SMSSDK.EVENT_GET_VERIFICATION_CODE -> { //验证码获取成功
                            Log.d("caicai", "获取：result = $result --- $data")
                        }
                        SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE -> { //验证码验证成功
                            Log.d("caicai", "提交：result = $result --- $data")
                        }
                    }
                } else {
                    data as Throwable
                    Log.d("caicai", "错误：result = $result --- ${data.message}")
                }
            }
        }
        SMSSDK.registerEventHandler(eventHandler) //注册短信回调
    }

    companion object {
        fun sendCode(phone: String) {
            SMSSDK.getVerificationCode("86", phone)
        }

        fun submitCode(phone: String, code: String) {
            SMSSDK.submitVerificationCode("86", phone, code)
        }
    }
}