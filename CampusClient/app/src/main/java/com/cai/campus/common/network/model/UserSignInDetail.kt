package com.cai.campus.common.network.model

import java.io.Serializable

class UserSignInDetail : Serializable {
    var signIn: SignIn? = null
    var record: SignInRecord? = null
    var groupName: String? = null

    companion object {
        private const val serialVersionUID = 628352243016151750L
    }

    override fun toString(): String {
        return "UserSignInDetail(signIn=$signIn, record=$record, groupName=$groupName)"
    }


}