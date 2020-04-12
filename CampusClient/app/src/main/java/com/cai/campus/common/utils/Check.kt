package com.cai.campus.common.utils

import android.text.TextUtils

object Check {
    /**
     * 检查是否是电话号码
     *
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、177、178、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    fun isMobileNum(number: String): Boolean {
        val num = "[1][3578]\\d{9}"
        return if (TextUtils.isEmpty(number)) {
            false
        } else {
            number.matches(Regex(num))
        }
    }
}