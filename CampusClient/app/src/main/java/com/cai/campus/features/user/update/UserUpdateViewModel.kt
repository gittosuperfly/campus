package com.cai.campus.features.user.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class UserUpdateViewModel : ViewModel() {
    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    fun updateUserInfo(userName: String, email: String, sex: Int, intro: String) {
        val userInfo = localStorage.lastLoginUser

        userInfo.name = userName
        userInfo.email = email
        userInfo.sex = sex
        userInfo.introduction = intro

        viewModelScope.launch {
            val response = api.updateUser(userInfo)
            Prompt.show(response.message)
            if (response.result == 1) {
                localStorage.lastLoginUser = userInfo
            }
        }
    }

    fun getUser(): UserAccount {
        return localStorage.lastLoginUser
    }
}