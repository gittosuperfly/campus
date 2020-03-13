package com.cai.campus.test_push

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PushTestService {
    @GET("push/test/{alter}")
    fun pushAsync(@Path("alter") alter: String): LiveData<Boolean>
}