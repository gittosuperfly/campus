package com.cai.campus.appFramework.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        delegate.setContentView(layoutResID)
        start()
    }

    private fun start() {
        Logger.d("Activity create! run start")
        initView()
        initData()
        subscribeOnView()
    }

    open fun initView() {}
    open fun initData() {}
    open fun subscribeOnView() {}

}