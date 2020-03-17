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

    /**
     * 简化Toast操作
     * @param target 信息对象
     */
    open fun show(target: Any?) {
        val message = target?.toString() ?: "null"
        // 去除一些手机系统 Toast 在消息前面添加的App名称
        val toast: Toast = Toast.makeText(BaseApplication.context, null, Toast.LENGTH_SHORT)
        toast.setText(message)
        toast.show()
    }

    private fun start() {
        initView()
        initData()
        subscribeOnView()
    }

    open fun initView() {}
    open fun initData() {}
    open fun subscribeOnView() {}

}