package com.cai.campus.app

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    fun initActivity(current: Activity, @LayoutRes layoutResID: Int) {
        current.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        delegate.setContentView(layoutResID)
        start()
    }

    private fun start() {
        init()
        subscribeOnView()
    }

    open fun init() {}
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