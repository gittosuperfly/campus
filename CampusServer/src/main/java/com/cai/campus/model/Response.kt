package com.cai.campus.model

class Response<T> private constructor(
        val result: Int,
        val message: String? = null,
        val data: Any? = null) {
    companion object {
        @JvmStatic
        fun <T> ok(): Response<T> {
            return Response(result = ResultCode.SUCCESS)
        }

        @JvmStatic
        fun <T> ok(data: T?): Response<T> {
            return Response(result = ResultCode.SUCCESS, data = data)
        }

        @JvmStatic
        fun <T> ok(message: String): Response<T> {
            return Response(result = ResultCode.SUCCESS, message = message)
        }

        @JvmStatic
        fun <T> error(result: @ResultCode Int, message: String): Response<T> {
            return Response(result = result, message = message)
        }

        @JvmStatic
        fun <T> error(result: @ResultCode Int, data: T?): Response<T> {
            return Response(result = result, data = data)
        }
    }
}