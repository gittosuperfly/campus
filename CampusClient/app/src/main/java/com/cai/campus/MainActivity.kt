package com.cai.campus

import android.os.Bundle
import android.view.View
import com.cai.campus.appFramework.base.BaseActivity

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)
    }

}
