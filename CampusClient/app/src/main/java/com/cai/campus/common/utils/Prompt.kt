package com.cai.campus.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cai.campus.app.BaseApplication

/**
 * Toast封装类
 */
class Prompt private constructor(private val builder: Builder) {

    private val context: Context = BaseApplication.getAppContext()
    private var toast: Toast? = null
    private var tv: TextView? = null
    private var container: View? = null

    fun show(resId: Int) {
        show(context.getString(resId))
    }

    fun show(message: String?, vararg any: Any) {
        if (TextUtils.isEmpty(message)) {
            return
        }

        var result = message as String
        if (any.isNotEmpty()) {
            result = String.format(message, any)
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            showInternal(result)
        } else {
            mainHandler.post { showInternal(result) }
        }
    }

    /** 获取Toast的View进行使用，只支持自定义样式的Toast，若为系统默认Toast样式，则返回null*/
    fun getView(): View? {
        if (container == null && builder.isDefault.not()) {
            createToastIfNeeded()
        }
        return container
    }

    private fun showInternal(message: String) {
        createToastIfNeeded()

        if (builder.isDefault) {
            toast?.setText(message)
            toast?.show()
        } else {
            tv?.text = message
            toast?.show()
        }
    }

    @SuppressLint("ShowToast")
    private fun createToastIfNeeded() {
        if (toast == null) {
            if (builder.isDefault) {
                toast = Toast.makeText(context, "", builder.duration)
            } else {
                container =
                    builder.layout ?: LayoutInflater.from(context).inflate(builder.layoutId, null)
                tv = container?.findViewById(builder.tvId)
                toast = Toast(context)
                toast?.view = container
                toast?.duration = builder.duration
            }

            if (builder.gravity != 0) {
                toast?.setGravity(builder.gravity, builder.offsetX, builder.offsetY)
            }
        }
    }

    companion object {

        private val mainHandler = Handler(Looper.getMainLooper())

        /**
         * 默认提供的Toast实例，在首次使用时进行加载。
         */
        @JvmStatic
        val DEFAULT: Prompt by lazy { return@lazy newBuilder().build() }

        fun show(resId: Int) {
            DEFAULT.show(resId)
        }

        fun show(message: String?, vararg any: Any) {
            DEFAULT.show(message, any)
        }

        @JvmStatic
        fun newBuilder(): Builder {
            return Builder(isDefault = true)
        }

        @JvmStatic
        fun newBuilder(layoutId: Int, tvId: Int): Builder {
            return Builder(isDefault = false, layoutId = layoutId, tvId = tvId)
        }

        @JvmStatic
        fun newBuilder(layout: View, tvId: Int): Builder {
            assert(layout.parent == null)
            return Builder(isDefault = false, layout = layout, tvId = tvId)
        }
    }

    class Builder internal constructor(
        internal var isDefault: Boolean,
        internal var layout: View? = null,
        internal var layoutId: Int = 0,
        internal var tvId: Int = 0
    ) {

        internal var duration: Int = Toast.LENGTH_SHORT
        internal var gravity: Int = 0
        internal var offsetX: Int = 0
        internal var offsetY: Int = 0

        fun setGravity(gravity: Int, offsetX: Int, offsetY: Int): Builder {
            this.gravity = gravity
            this.offsetX = offsetX
            this.offsetY = offsetY
            return this
        }

        fun setDuration(duration: Int): Builder {
            this.duration = duration
            return this
        }

        fun build(): Prompt {
            return Prompt(this)
        }
    }
}