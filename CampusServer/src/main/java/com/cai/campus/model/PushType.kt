package com.cai.campus.model

@Target(
        AnnotationTarget.TYPE,
        AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.SOURCE)
annotation class PushType {
    companion object {
        const val SIGN_IN = 1
        const val MESSAGE = 2
    }
}