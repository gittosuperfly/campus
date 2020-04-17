package com.cai.campus.features.home

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.router.RouterPath
import kotlinx.android.synthetic.main.home_activity.*

@Route(path = RouterPath.HOME_PAGE)
class HomeActivity : BaseActivity() {

    private val appRepo = LocalRepoManager.load(AppData::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.home_activity)
    }

    override fun init() {
        userPhoneTv.text = "当前用户：" + if (appRepo.isLogin) appRepo.lastLoginPhone else "未登录"

        exitUserBtn.setOnClickListener {
            appRepo.isLogin = false
            appRepo.apply()
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
            finish()
        }
    }
}
