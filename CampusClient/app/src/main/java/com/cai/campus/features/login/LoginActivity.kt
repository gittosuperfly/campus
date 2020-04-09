package com.cai.campus.features.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.common.router.RouterPath

@Route(path = RouterPath.LOGIN_PAGE)
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }
}
