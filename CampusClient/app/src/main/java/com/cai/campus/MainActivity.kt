package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.sms.SMSManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)

        SMSManager({
            show("获取验证码成功")
            show.text = "获取验证码成功"
        }, {
            show("验证成功")
            show.text = "验证成功"
        }, {
            show.text = it.message
        })

    }

    override fun initView() {
        super.initView()
        hello.setOnClickListener {
            SMSManager.sendCode("15091200140")
        }

        submit.setOnClickListener {
            SMSManager.submitCode("15091200140", code.text.toString())
        }

        show.setOnClickListener {
            show("??????")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        SMSManager.unregister()
    }
}
