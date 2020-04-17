package com.cai.campus.common.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class LoggerInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        printRequestMessage(request)
        val response = chain.proceed(request)
        printResponseMessage(response)
        return response
    }

    /**
     * 打印请求消息
     *
     * @param request 请求的对象
     */
    private fun printRequestMessage(request: Request?) {
        if (request == null) {
            return
        }
        Log.i(
            TAG,
            "Url   : " + request.url().url().toString()
        )
        Log.i(TAG, "Method: " + request.method())
        Log.i(TAG, "Heads : " + request.headers())
        val requestBody = request.body() ?: return
        try {
            val bufferedSink = Buffer()
            requestBody.writeTo(bufferedSink)
            var charset = requestBody.contentType()!!.charset()
            charset = charset ?: Charset.forName("utf-8")
            Log.i(
                TAG,
                "Params: " + bufferedSink.readString(charset)
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private fun printResponseMessage(response: Response?) {
        if (response == null || !response.isSuccessful) {
            return
        }
        val responseBody = response.body()
        val contentLength = responseBody!!.contentLength()
        val source = responseBody.source()
        try {
            source.request(Long.MAX_VALUE) // Buffer the entire body.
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer = source.buffer()
        var charset = StandardCharsets.UTF_8
        val contentType = responseBody.contentType()
        if (contentType != null) {
            charset = contentType.charset()
        }
        if (contentLength != 0L) {
            val result = buffer.clone().readString(charset)
            Log.i(TAG, "Response: $result")
        }
    }

    companion object {
        const val TAG = "NetWorkLogger"
    }
}