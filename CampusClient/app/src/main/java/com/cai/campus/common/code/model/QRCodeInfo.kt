package com.cai.campus.common.code.model

open class QRCodeInfo(val handlerType: Int, val value: Int) {

    companion object {
        const val TYPE_ADD_GROUP = 1
        const val TYPE_CLOCK_IN = 2
    }

    override fun toString(): String {
        return "QRCodeInfo(handlerType=$handlerType, value=$value)"
    }
}