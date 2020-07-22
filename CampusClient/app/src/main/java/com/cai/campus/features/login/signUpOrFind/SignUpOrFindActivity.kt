package com.cai.campus.features.login.signUpOrFind

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var viewModel: SignUpOrFindViewModel

    private var isCountdown = false
    private var type: Int? = 0
    private var pageName: String? = ""

    private var inputPhone: String = ""
    private var inputPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.signup_or_find_activity)

        type = intent.getIntExtra("type", 0)
        pageName = intent.getStringExtra("pageName")

        pageNameTv.text = pageName
        submitBtn.text = when (type) {
            1 -> "提交注册"
            2 -> "重置密码"
            else -> ""
        }
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(SignUpOrFindViewModel::class.java)

        ShortMessageServer({
            Prompt.show("获取验证码成功")
            countdown()
        }, {
            if (type == 1) {
                viewModel.registerUser(inputPhone, inputPassword)
            } else {
                viewModel.resetPassword(inputPhone, inputPassword)
            }
        }, {
            Prompt.show(it.message)
        })

        subscribeViewEvent()
        setViewClickListener()
    }


    override fun subscribeOnView() {
        viewModel.msg.observe(this, Observer {
            Prompt.show(it)
        })

        viewModel.isFinish.observe(this, Observer {
            if (it) {
                finish()
            }
        })
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
                    ShortMessageServer.sendCode(
                        phone,
                        if (type == 1) ShortMessageServer.CREATE_CODE else ShortMessageServer.RESET_CODE
                    )
                } else {
                    Prompt.show("请稍后")
                }
            } else {
                Prompt.show("请检查您的手机号码是否正确")
            }
        }

        submitBtn.setOnClickListener {
            inputPhone = phoneEdit.text.toString()
            inputPassword = passwordEdit.text.toString()
            val repeatPassword = repeatPasswordEdit.text.toString()
            val code = codeEdit.text.toString()


            if (inputPassword == repeatPassword) {
                ShortMessageServer.submitCode(inputPhone, code)
            } else {
                Prompt.show("两次密码不一致")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun countdown() {
        isCountdown = true
        Countdown.start(60L, {
            getCodeBtn.text = "请稍后:$it"
            phoneEdit.isEnabled = false
        }, {
            isCountdown = false
            getCodeBtn.text = "获取验证码"
            phoneEdit.isEnabled = true
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
