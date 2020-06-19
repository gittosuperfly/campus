package com.cai.campus.model

import javax.validation.constraints.Null

class PushResponse<T> private constructor(
        val type: Int,
        val data: Any? = null) {
    companion object {
        @JvmStatic
        fun <T>get(type: @PushType Int, data: T): PushResponse<Any> {
            return PushResponse(type, data)
        }
    }
}