package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cai.campus.app.BaseActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)


        show("大家好才是真的好")
    }

}
