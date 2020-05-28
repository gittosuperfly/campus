package com.cai.campus.common.repository.repo

import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.preference.PreferenceRename
import com.cai.campus.common.repository.preference.PreferenceSupport

@PreferenceRename("app_data_repo")
class AppData : PreferenceSupport() {
    var isLogin: Boolean = false
    var lastLoginPhone: String = ""
    lateinit var lastLoginUser: UserAccount
}