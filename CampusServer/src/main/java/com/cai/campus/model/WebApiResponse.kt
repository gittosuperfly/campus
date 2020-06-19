package com.cai.campus.model

import javax.validation.constraints.Null

class WebApiResponse<T> private constructor(
        val result: Int,
        val message: String? = null,
        val data: Any? = null) {
    companion object {

        @JvmStatic
        fun get(result: @ResultCode Int): WebApiResponse<Null> {
            return WebApiResponse(result = result)
        }

        @JvmStatic
        fun get(result: @ResultCode Int, message: String): WebApiResponse<Null> {
            return WebApiResponse(result = result, message = message)
        }

        @JvmStatic
        fun <T> get(result: @ResultCode Int, data: T?): WebApiResponse<T> {
            return WebApiResponse(result = result, data = data)
        }

        @JvmStatic
        fun <T> get(result: @ResultCode Int, message: String, data: T?): WebApiResponse<T> {
            return WebApiResponse(result = result, message = message, data = data)
        }

    }
}