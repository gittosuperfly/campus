package com.cai.campus.features.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    private val _apiData = MutableLiveData<Response<String?>>()
    val apiData: LiveData<Response<String?>> = _apiData

    fun login(phone: String, password: String) {
        viewModelScope.launch {

            val data = api.userLogin(phone, password)
            _apiData.value = data

            if (data.result == 1) {
                localStorage.isLogin = true
                localStorage.lastLoginPhone = phone
                localStorage.apply()
                getUser()
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val data = api.queryUser("phone", localStorage.lastLoginPhone)
            if (data.result == 1) {
                localStorage.lastLoginUser = data.data!!
                localStorage.apply()
            }
        }
    }


    fun isGoHome(): Boolean {
        return localStorage.isLogin
//        return false
    }
}