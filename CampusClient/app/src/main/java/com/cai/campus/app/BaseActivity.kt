package com.cai.campus.app

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        delegate.setContentView(layoutResID)
        start()
    }

    private fun start() {
        initData()
        initView()
        subscribeOnView()
    }

    open fun initData() {}
    open fun initView() {}
    open fun subscribeOnView() {}

}