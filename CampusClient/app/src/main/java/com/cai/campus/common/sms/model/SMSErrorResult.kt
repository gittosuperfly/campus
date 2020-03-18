package com.cai.campus.common.sms.model

import com.google.gson.annotations.SerializedName

class SMSErrorResult {

    @SerializedName("status")
    var code: String = ""

    @SerializedName("detail")
    var message: String = ""

}