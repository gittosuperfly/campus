package com.cai.campus.common.network.api

import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.network.model.GroupUser
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.network.model.UserAccount
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroupApiServer {

    @POST("/api/group/create")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun createGroup(
        @Field("groupName") groupName: String,
        @Field("creatorUid") creatorUid: Int
    ): Response<String?>

    @POST("/api/group/addGroup")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun addGroup(
        @Field("userId") userId: Int,
        @Field("groupId") groupId: Int
    ): Response<String?>


    @POST("/api/group/queryUserAllGroup")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryUserAllGroup(
        @Field("userId") userId: Int
    ): Response<List<GroupAccount>>


    @POST("/api/group/queryGroupAllUser")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun queryGroupAllUser(
        @Field("groupId") groupId: Int
    ): Response<List<GroupUser>>

}
