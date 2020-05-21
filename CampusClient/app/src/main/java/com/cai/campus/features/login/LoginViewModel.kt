package com.cai.campus.features.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.LoginApiServer
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(LoginApiServer::class.java)
    private val appRepo = LocalRepoManager.load(AppData::class.java)

    private val _apiData = MutableLiveData<Response<String?>>()
    val apiData: LiveData<Response<String?>> = _apiData

    fun login(phone: String, password: String) {
        viewModelScope.launch {

            val data = api.userLogin(phone, password)
            _apiData.value = data

            if (data.result == 1) {
                appRepo.isLogin = true
                appRepo.lastLoginPhone = phone
                appRepo.apply()
            }
        }
    }


    fun isGoHome(): Boolean {
//        return appRepo.isLogin
        return false
    }
}