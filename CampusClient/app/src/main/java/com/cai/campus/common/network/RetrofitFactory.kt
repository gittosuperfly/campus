package com.cai.campus.common.network

import com.cai.campus.common.network.gson.adapter.DoubleDefaultAdapter
import com.cai.campus.common.network.gson.adapter.IntegerDefaultAdapter
import com.cai.campus.common.network.gson.adapter.LongDefaultAdapter
import com.cai.campus.common.network.gson.adapter.StringNullAdapter
import com.cai.campus.common.network.interceptor.LoggerInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {

    private val retrofit: Retrofit

    init {
        val gson = Gson().newBuilder()
            .setLenient()
            .serializeNulls()
            .registerTypeAdapter(Int::class.java, IntegerDefaultAdapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefaultAdapter())
            .registerTypeAdapter(Double::class.java, DoubleDefaultAdapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleDefaultAdapter())
            .registerTypeAdapter(Long::class.java, LongDefaultAdapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongDefaultAdapter())
            .registerTypeAdapter(String::class.java, StringNullAdapter())
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://120.55.44.23")
            .client(initOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }


    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)//设置重连
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(getAuthInspectInterceptor())
            .addInterceptor(LoggerInterceptor())
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    private fun getAuthInspectInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val authorised: Request = originalRequest.newBuilder()
                .header(TOKEN, TOKEN_VALUE)
                .build()
            chain.proceed(authorised)
        }
    }


    companion object {

        const val TOKEN = "Access-User-Token"
        const val TOKEN_VALUE = "EwPhXpFJ6hj*!VmJREkPL8U%Bqs3WrlGAIUKh!7n!#RYXzm%T5r2HMhgo2BhtFtt"

        val instance: RetrofitFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }

    }

}