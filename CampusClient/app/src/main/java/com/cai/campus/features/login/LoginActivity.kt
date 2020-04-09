package com.cai.campus.features.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.login_activity.*

@Route(path = RouterPath.LOGIN_PAGE)
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.login_activity)

        loginPhoneEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                loginPhoneEdit.hint = ""
            } else {
                loginPhoneEdit.hint = "手机号"
            }
        }

        loginPasswordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                loginPasswordEdit.hint = ""
            } else {
                loginPasswordEdit.hint = "密码"
            }
        }

        loginBtn.setOnClickListener {
            Prompt.show("登录中...")
        }
    }
}