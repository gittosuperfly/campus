package com.cai.campus.common.network.model

import java.io.Serializable

class UserAccount : Serializable {
    constructor() {}
    constructor(phone: String?, password: String?) {
        this.phone = phone
        this.password = password
    }

    var uid: Int? = null
    var phone: String? = null
    var password: String? = null
    var name: String? = null
    var sex: Int? = null
    var logo: String? = null
    var birthday: Int? = null
    var introduction: String? = null
    var email: String? = null

    companion object {
        private const val serialVersionUID = 573386372958693835L
    }
}