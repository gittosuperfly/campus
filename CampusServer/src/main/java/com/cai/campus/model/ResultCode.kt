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

        //login error
        const val LOGIN_PHONE_ERROR = 1001
        const val LOGIN_PASSWORD_ERROR = 1002

        //query error
        const val QUERY_USER_TYPE_ERROR = 1001

        //register error
        const val REGISTER_ERROR = 1101

        //update error
        const val UPDATE_USER_ERROR = 1201
    }
}