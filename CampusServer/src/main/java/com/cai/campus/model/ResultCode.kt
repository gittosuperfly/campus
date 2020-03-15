package com.cai.campus.model

@Target(
        AnnotationTarget.TYPE,
        AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.SOURCE)
annotation class ResultCode {
    companion object {
        const val SUCCESS: Int = 1

        const val BAD_REQUEST = 400 //请求参数有误
        const val UNAUTHORIZED = 401 //未授权
        const val FORBIDDEN = 403 //拒绝访问
        const val NOT_FOUND = 404
        const val GONE = 410
        const val PARAM_REQUIRED = 411
        const val PARAM_VALIDATION_FAILURE = 412
        const val OPERATION_EXPIRED = 413
        const val OPERATION_DUPLICATED = 414
        const val OPERATION_UNSUPPORTED = 415

        const val INTERNAL_SERVER_ERROR = 500
        const val NOT_IMPLEMENTED = 501
        const val BAD_GATEWAY = 502
        const val SERVICE_UNAVAILABLE = 503
        const val GATEWAY_TIMEOUT = 504
    }
}