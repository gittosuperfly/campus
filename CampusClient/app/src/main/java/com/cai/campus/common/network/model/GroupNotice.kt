package com.cai.campus.common.network.model

import java.io.Serializable

class GroupNotice : Serializable {
    var id: Int? = null
    var groupId: Int? = null
    var uid: Int? = null
    var releaseTime: Long? = null
    var notice: String? = null

    companion object {
        private const val serialVersionUID = 784679973965363400L
    }

    override fun toString(): String {
        return "GroupNotice(id=$id, groupId=$groupId, uid=$uid, releaseTime=$releaseTime, notice=$notice)"
    }


}