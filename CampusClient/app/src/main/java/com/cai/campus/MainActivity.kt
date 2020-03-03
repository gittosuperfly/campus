package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cai.campus.app.BaseActivity
import com.cai.campus.app.BaseApplication
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {



    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)

        hello.setOnClickListener {
            Toast.makeText(BaseApplication.context,"666",Toast.LENGTH_SHORT)
            toast("？？？")
        }
    }

}
