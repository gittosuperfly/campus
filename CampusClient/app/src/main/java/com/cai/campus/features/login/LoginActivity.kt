package com.cai.campus.features.login

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.login_activity.*


@Route(path = RouterPath.LOGIN_PAGE)
class LoginActivity : BaseActivity() {

    val viewModel: LoginViewModel = LoginViewModel()

    var loginBtnType = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.login_activity)

        viewModel.msg.observe(this, Observer {

        })

    }

    override fun initView() {
        subscribeViewEvent()
        setViewClickListener()
        setViewAnimation()
    }

    override fun subscribeOnView() {

    }

    /* 设置页面点击事件 */
    private fun setViewClickListener() {
        loginBtn.setOnClickListener {
            if (loginBtnType) {
                viewModel.push()
            } else {
                Prompt.show("请检查账号密码")
            }
        }

        registerBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouterPath.REGISTER_PAGE)
                .withInt("type", 1)
                .withString("pageName", "新用户注册")
                .navigation()
        }

        findPasswordBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouterPath.REGISTER_PAGE)
                .withInt("type", 2)
                .withString("pageName", "找回密码")
                .navigation()
        }
    }

    /* 设置页面动画 */
    private fun setViewAnimation() {
        ObjectAnimator.ofFloat(loginFormLayout, "translationY", 250f, 0f)
            .setDuration(1000).start()
    }

    /* 设置页面监听 */
    private fun subscribeViewEvent() {
        loginPhoneEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                loginPhoneEdit.hint = ""
            } else {
                loginPhoneEdit.hint = getString(R.string.phoneHint)
            }
        }

        loginPasswordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                loginPasswordEdit.hint = ""
            } else {
                loginPasswordEdit.hint = getString(R.string.passwordHint)
            }
        }

        loginPasswordEdit.setTextChangeListener {
            changeLoginBtnBackground()
        }
        loginPhoneEdit.setTextChangeListener {
            changeLoginBtnBackground()
        }
    }


    private fun changeLoginBtnBackground() {
        if (loginPhoneEdit.text.isNotEmpty() && loginPasswordEdit.text.isNotEmpty()) {
            if (!loginBtnType) {
                loginBtn.setBackgroundResource(R.drawable.shape_login_btn_on)
                loginBtnType = true
            }
        } else {
            if (loginBtnType) {
                loginBtn.setBackgroundResource(R.drawable.shape_login_btn_off)
                loginBtnType = false
            }
        }
    }
}