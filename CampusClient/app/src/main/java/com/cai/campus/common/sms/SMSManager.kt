package com.cai.campus.common.sms

import android.annotation.SuppressLint
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.cai.campus.common.sms.model.SMSErrorResult
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class SMSManager(
    onSendOk: () -> Unit,
    onCheckOk: () -> Unit,
    onError: (errorResult: SMSErrorResult) -> Unit
) {
    init {
        smsEventDispatch(onSendOk, onCheckOk, onError)
    }

    private fun smsEventDispatch(
        onSendOk: () -> Unit,
        onCheckOk: () -> Unit,
        onError: (errorResult: SMSErrorResult) -> Unit
    ) {
        eventHandler = object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    when (event) {
                        SMSSDK.EVENT_GET_VERIFICATION_CODE -> { //验证码获取成功
                            toMainThread(onSendOk)
                        }
                        SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE -> { //验证码验证成功
                            toMainThread(onCheckOk)
                        }
                    }
                } else {
                    data as Throwable
                    val errResult = Gson().fromJson(data.message, SMSErrorResult::class.java)
                    toMainThread(onError, errResult)
                }
            }
        }
        SMSSDK.registerEventHandler(eventHandler)
    }

    /**
     * 去主线程运行方法。方便更新UI操作
     */
    @SuppressLint("CheckResult")
    private fun toMainThread(func: () -> Unit) {
        Observable.just(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                func()
            }
    }

    @SuppressLint("CheckResult")
    private fun toMainThread(func: (result: SMSErrorResult) -> Unit, result: SMSErrorResult) {
        Observable.just(result)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                func(it)
            }
    }

    companion object {

        var eventHandler: EventHandler? = null

        fun sendCode(phone: String) {
            SMSSDK.getVerificationCode("86", phone)
        }

        fun submitCode(phone: String, code: String) {
            SMSSDK.submitVerificationCode("86", phone, code)
        }

        /**
         * 注销监听，防止内存泄漏
         */
        fun unregister() {
            SMSSDK.unregisterEventHandler(eventHandler)
        }
    }
}