package com.cai.campus.model

@Target(
        AnnotationTarget.TYPE,
        AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.SOURCE)
annotation class ResultCode {
    companion object {
        //ok
        const val SUCCESS: Int = 1

        //http
        const val BAD_REQUEST = 400 //请求参数有误
        const val UNAUTHORIZED = 401 //访问未授权
        const val FORBIDDEN = 403 //拒绝访问
    }
}