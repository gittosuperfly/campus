package com.cai.campus

import com.cai.campus.common.repository.preference.PreferenceRename
import com.cai.campus.common.repository.preference.PreferenceSupport
import java.security.PermissionCollection

@PreferenceRename("user_info")
class User : PreferenceSupport() {
    var username: String = ""
    var age: Int = 0
    var address: String = ""
}