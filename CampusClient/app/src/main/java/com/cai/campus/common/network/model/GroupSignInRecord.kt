package com.cai.campus.common.network.model

class GroupSignInRecord(var record: SignInRecord, var userName: String) {
    override fun toString(): String {
        return "GroupSignInRecord(record=$record, userName='$userName')"
    }
}