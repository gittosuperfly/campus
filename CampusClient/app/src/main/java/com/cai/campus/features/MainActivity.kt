package com.cai.campus.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import com.cai.campus.features.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.MAIN_PAGE)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home.setOnClickListener {
            ARouter.getInstance().build(RouterPath.HOME_PAGE).navigation()
        }

        login.setOnClickListener {
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
        }

    }
}
