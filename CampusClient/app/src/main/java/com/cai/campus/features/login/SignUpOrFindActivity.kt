package com.cai.campus.features.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.sms.ShortMessageServer
import com.cai.campus.common.utils.Check
import com.cai.campus.common.utils.Countdown
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.signup_or_find_activity.*


@Route(path = RouterPath.REGISTER_PAGE)
class SignUpOrFindActivity : BaseActivity() {

    var isCountdown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.signup_or_find_activity)

        val type = intent.getIntExtra("type", 0)
        val pageName = intent.getStringExtra("pageName")


        pageNameTv.text = pageName
        submitBtn.text = when (type) {
            1 -> "提交注册"
            2 -> "重置密码"
            else -> ""
        }
    }


    override fun initData() {
        super.initData()
        ShortMessageServer({
            Prompt.show("获取验证码成功")
            countdown()
        }, {
            Prompt.show("验证成功")
        }, {
            Prompt.show(it.message)
        })
    }

    override fun initView() {
        super.initView()
        subscribeViewEvent()
        setViewClickListener()

    }

    override fun onDestroy() {
        super.onDestroy()
        ShortMessageServer.unregister()
    }

    private fun setViewClickListener() {
        getCodeBtn.setOnClickListener {
            val phone = phoneEdit.text.toString()
            if (phone.isNotEmpty() && Check.isMobileNum(phone)) {
                if (!isCountdown) {
                    ShortMessageServer.sendCode(phone, ShortMessageServer.CREATE_CODE)
                } else {
                    Prompt.show("请稍后")
                }
            } else {
                Prompt.show("请检查您的手机号码是否正确")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun countdown() {
        isCountdown = true
        Countdown.start(60L, {
            if (getCodeBtn != null) {
                getCodeBtn.text = "请稍后:$it"
            }
        }, {
            isCountdown = false
            getCodeBtn.text = "获取验证码"
        })
    }

    private fun subscribeViewEvent() {
        phoneEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                phoneEdit.hint = ""
            } else {
                phoneEdit.hint = getString(R.string.phoneHint)
            }
        }

        passwordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEdit.hint = ""
            } else {
                passwordEdit.hint = getString(R.string.setPasswordHint)
            }
        }

        repeatPasswordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                repeatPasswordEdit.hint = ""
            } else {
                repeatPasswordEdit.hint = getString(R.string.repeatPasswordHint)
            }
        }

        codeEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                codeEdit.hint = ""
            } else {
                codeEdit.hint = getString(R.string.codeHint)
            }
        }
    }
}
