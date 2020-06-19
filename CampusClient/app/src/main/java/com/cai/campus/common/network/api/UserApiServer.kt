package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.Response
import com.cai.campus.common.network.model.UserAccount
import retrofit2.http.*

interface UserApiServer {

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
        @Field("password") password: String,
        @Field("UUID") UUID: String
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

    @POST("/api/user/update")
    suspend fun updateUser(
        @Body user: UserAccount
    ): Response<UserAccount?>

}