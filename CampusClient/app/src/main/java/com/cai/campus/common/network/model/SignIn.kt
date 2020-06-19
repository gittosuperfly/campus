package com.cai.campus.common.network.model

import java.io.Serializable

/**
 * (SignIn)实体类
 *
 * @author makejava
 * @since 2020-06-01 16:09:24
 */
class SignIn : Serializable {
    var signId: Int? = null
    var groupId: Int? = null
    var createTime: Long? = null
    var endTime: Long? = null
    var uid: Int? = null
    var location: String? = null
    var detail: String? = null

    constructor() {}
    constructor(groupId: Int?) {
        this.groupId = groupId
    }

    companion object {
        private const val serialVersionUID = -62269484241224203L
    }

    override fun toString(): String {
        return "SignIn(signId=$signId, groupId=$groupId, createTime=$createTime, endTime=$endTime, uid=$uid, location=$location, detail=$detail)"
    }


}