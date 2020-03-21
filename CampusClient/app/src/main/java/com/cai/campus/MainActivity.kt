package com.cai.campus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.repository.LocalRepository
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.sms.SMSManager
import com.cai.campus.common.toast.Toast
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.MAIN_PAGE)
class MainActivity : BaseActivity() {


    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)

        SMSManager({
            Toast.DEFAULT.show("获取验证码成功")
            show.text = "获取验证码成功"
        }, {
            Toast.DEFAULT.show("验证成功")
            show.text = "验证成功"
        }, {
            show.text = it.message
        })

    }

    override fun initView() {

        var user = LocalRepository.load(User::class.java)

        super.initView()
        hello.setOnClickListener {
            SMSManager.sendCode("15091200140")
        }

        submit.setOnClickListener {
            SMSManager.submitCode("15091200140", code.text.toString())
            user.username = "caiyufei"
            user.age = 14
            user.apply()
        }

        show.setOnClickListener {
//            ARouter.getInstance().build("/route/test").navigation()

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        SMSManager.unregister()
    }
}
