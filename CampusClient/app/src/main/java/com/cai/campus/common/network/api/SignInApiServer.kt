package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.GroupSignInRecord
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.network.model.SignIn
import com.cai.campus.common.network.model.UserSignInDetail
import retrofit2.http.*

interface SignInApiServer {
    @POST("api/signIn/release")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun releaseSignIn(
        @Field("uid") uid: Int,
        @Field("groupId") groupId: Int,
        @Field("createTime") createTime: Long,
        @Field("endTime") endTime: Long,
        @Field("location") location: String,
        @Field("detail") detail: String
    ): Response<String?>

    @POST("api/signIn/goSignIn")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun goSignIn(
        @Field("signInId") signInId: Int,
        @Field("recordId") recordId: Int,
        @Field("location") location: String,
        @Field("UUID") UUID: String
    ): Response<String?>


    @POST("api/signIn/queryAllSignIn")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryAllSignIn(
        @Field("groupId") groupId: Int
    ): Response<List<SignIn>>


    @POST("api/signIn/queryRecordList")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryRecordList(
        @Field("sid") signInId: Int
    ): Response<List<GroupSignInRecord>>


    @POST("api/signIn/queryUserAllRecordList")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryUserAllRecordList(
        @Field("userId") userId: Int
    ): Response<List<UserSignInDetail>>

}