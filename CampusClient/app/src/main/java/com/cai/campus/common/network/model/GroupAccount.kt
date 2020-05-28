package com.cai.campus.common.network.model

import java.io.Serializable

class GroupAccount : Serializable {
    var groupId: Int = 0
    var createTime: Long? = null
    var name: String? = null
    var logo: String? = null

    companion object {
        private const val serialVersionUID = 947758087054179715L
    }

    override fun toString(): String {
        return "GroupAccount(groupId=$groupId, createTime=$createTime, name=$name, logo=$logo)"
    }


}