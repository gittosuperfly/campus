package com.cai.campus.features.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)

    private val _user = MutableLiveData<UserAccount>()
    val user: LiveData<UserAccount> = _user

    init {
        updateUserInfo()
    }

    fun updateUserInfo() {
        viewModelScope.launch {
            val response = api.queryUser("id", localStorage.lastLoginUser.uid.toString())
            if (response.result == 1) {
                localStorage.lastLoginUser = response.data!!
                localStorage.apply()

                val userInfo = localStorage.lastLoginUser
                if (userInfo.name.isNullOrEmpty() || userInfo.name == "")
                    userInfo.name = "未命名用户$userInfo.uid"
                _user.value = userInfo
            }
        }
    }

    fun quitLogin() {
        localStorage.isLogin = false
        localStorage.apply()
    }

}
