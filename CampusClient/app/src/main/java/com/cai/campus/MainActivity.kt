package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.sms.SMSManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)
        SMSManager()

        hello.setOnClickListener {
            SMSManager.sendCode("15091200140")
        }

        submit.setOnClickListener {
            SMSManager.submitCode("15091200140", code.text.toString())
        }


    }

}
