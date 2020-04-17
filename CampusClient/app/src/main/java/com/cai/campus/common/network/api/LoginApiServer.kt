package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.Response
import com.cai.campus.common.network.model.UserAccount
import retrofit2.http.*

interface LoginApiServer {

    /**
     * 使用协程进行网络请求
     */

    @POST("/push")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun pushTest(): Response<String?>

    @POST("/test")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun test(
        @Field("value") value: Int
    ): Response<Test>


    @POST("/api/user/login")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun userLogin(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<String?>

    @POST("/api/user/register")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun userRegister(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<String?>

    @POST("/api/user/resetPassword")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun resetPassword(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<String?>

    @POST("/api/user/query/{type}")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryUser(
        @Path("type") type: String,
        @Field("value") password: String
    ): Response<UserAccount?>


}

class Test() {
    val message: String? = ""
    val code: Int? = 1
}