package com.cai.campus.common.network.model

open class Response<T>(val result: Int, val message: String, val data: T)

