package com.cai.campus.common.network.model

import java.io.Serializable

class GroupMessage : Serializable  {
    var notice: GroupNotice? = null
    var groupName: String? = null
    var createUserName: String? = null

    constructor() {}
    constructor(notice: GroupNotice?, groupName: String?, createUserName: String?) {
        this.notice = notice
        this.groupName = groupName
        this.createUserName = createUserName
    }

    companion object{
        private const val serialVersionUID = 78417392165463400L
    }

}