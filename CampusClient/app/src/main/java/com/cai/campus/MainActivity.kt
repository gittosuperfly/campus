package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cai.campus.app.BaseActivity
import com.cai.campus.test_push.LiveDataCallAdapterFactory
import com.cai.campus.test_push.PushTestService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity() {


    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)

        hello.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://120.55.44.23")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()

            retrofit.create(PushTestService::class.java)
                .pushAsync("caonima")
                .observe(this, Observer {
                    toast("ok")
                })
        }
    }


}
