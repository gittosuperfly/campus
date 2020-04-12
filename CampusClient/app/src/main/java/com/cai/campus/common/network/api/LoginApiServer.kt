package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.Response
import retrofit2.http.GET

interface LoginApiServer {

    /**
     * 使用协程进行网络请求
     */

    @GET("/push")
    suspend fun pushTest(): Response<String?>
}