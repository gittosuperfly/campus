package com.cai.campus.model

import javax.validation.constraints.Null

class Response<T> private constructor(
        val result: Int,
        val message: String? = null,
        val data: Any? = null) {
    companion object {

        @JvmStatic
        fun get(result: @ResultCode Int): Response<Null> {
            return Response(result = result)
        }

        @JvmStatic
        fun get(result: @ResultCode Int, message: String): Response<Null> {
            return Response(result = result, message = message)
        }

        @JvmStatic
        fun <T> get(result: @ResultCode Int, data: T?): Response<T> {
            return Response(result = result, data = data)
        }

        @JvmStatic
        fun <T> get(result: @ResultCode Int, message: String, data: T?): Response<T> {
            return Response(result = result, message = message, data = data)
        }

    }
}