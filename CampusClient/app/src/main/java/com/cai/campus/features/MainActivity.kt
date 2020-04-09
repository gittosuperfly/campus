package com.cai.campus.features

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.sms.ShortMessageServer
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.activity_main.*


@Route(path = RouterPath.MAIN_PAGE)
class MainActivity : BaseActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)

        ShortMessageServer({
            Prompt.show("获取验证码成功")
            show.text = "获取验证码成功"
        }, {
            Prompt.show("验证成功")
            show.text = "验证成功"
        }, {
            show.text = it.message
        })
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()
        hello.setOnClickListener {
            ShortMessageServer.sendCode("15091200140",ShortMessageServer.CREATE_CODE)
        }

        submit.setOnClickListener {
        }

        show.setOnClickListener {
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
        }
    }

    override fun subscribeOnView() {
        super.subscribeOnView()
    }

    override fun onDestroy() {
        super.onDestroy()
        ShortMessageServer.unregister()
    }
}
