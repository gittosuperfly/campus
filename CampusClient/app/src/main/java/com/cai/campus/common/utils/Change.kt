package com.cai.campus.common.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")

object Change {
    fun timestampToTime(seconds: String): String {
        val format = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date((seconds + "000").toLong()))
    }

    fun secondsToMin(seconds: Long): Long {
        return seconds / 60
    }
}