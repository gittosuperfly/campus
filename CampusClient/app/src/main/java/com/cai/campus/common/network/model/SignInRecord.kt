package com.cai.campus.common.network.model

import java.io.Serializable

class SignInRecord : Serializable {
    var id: Int? = null
    var signId: Int? = null
    var uid: Int? = null
    var isDone: Int? = null
    var doneTime: Long? = null
    var location: String? = null
    var isChangeUUID: Int? = 0


    companion object {
        private const val serialVersionUID = 638352643038151770L
    }

    override fun toString(): String {
        return "SignInRecord(id=$id, signId=$signId, uid=$uid, isDone=$isDone, doneTime=$doneTime, location=$location, isChangeUUID=$isChangeUUID)"
    }


}