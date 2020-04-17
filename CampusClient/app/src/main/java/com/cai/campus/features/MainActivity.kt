package com.cai.campus.features

import android.os.Bundle
import android.util.Log
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

    private val appRepo = LocalRepoManager.load(AppData::class.java)

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        hello.setOnClickListener {
            ARouter.getInstance().build(RouterPath.HOME_PAGE).navigation()
        }

        submit.setOnClickListener {
            Prompt.show(appRepo.isLogin.toString())
        }

        show.setOnClickListener {
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
        }

        queryBtn.setOnClickListener {
            val value = phoneEdit.text.toString().toInt()
            viewModel.test(value)
        }

        viewModel.msg.observe(this, Observer {
            Prompt.show(it)
        })

    }
}
