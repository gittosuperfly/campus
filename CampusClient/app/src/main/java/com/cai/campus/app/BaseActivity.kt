package com.cai.campus.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.mob.tools.utils.ReflectHelper.getClass

@SuppressLint("SetTextI18n")
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

    override fun onDestroy() {
        Log.d("ActivityLog", "<<< ${getRunningActivityName()}" )
        super.onDestroy()
    }

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

    private fun getRunningActivityName(): String {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningTasks(1)[0].topActivity!!.className
    }

}