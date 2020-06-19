package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.GroupMessage
import com.cai.campus.common.network.model.GroupNotice
import com.cai.campus.common.network.model.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface NoticeApiServer {
    @POST("/api/notice/release")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun releaseNotice(
        @Field("uid") uid: Int,
        @Field("groupId") groupId: Int,
        @Field("releaseTime") releaseTime: Long,
        @Field("detail") detail: String
    ): Response<String?>

    @POST("/api/notice/queryByUser")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryByUser(
        @Field("userId") userId: Int
    ): Response<List<GroupMessage>>

    @POST("/api/notice/delete")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun delete(
        @Field("userId") userId: Int,
        @Field("noticeId") noticeId: Int
    ): Response<List<GroupNotice>>


}