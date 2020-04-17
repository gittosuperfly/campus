package com.cai.campus.app

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        delegate.setContentView(layoutResID)
        //禁止旋转屏幕
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        start()
    }

    private fun start() {
        initViewModel()
        initData()
        initView()
        subscribeOnView()
    }

    open fun initViewModel(){}
    open fun initData() {}
    open fun initView() {}
    open fun subscribeOnView() {}


    /**
     * 设置一些扩展函数
     */

    //监听EditText文字变化
    fun EditText.setTextChangeListener(body: (key: String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                body(s.toString())
            }
        })
    }

}