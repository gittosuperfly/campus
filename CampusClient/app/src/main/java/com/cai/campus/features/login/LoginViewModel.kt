package com.cai.campus.features.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.LoginApiServer
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(LoginApiServer::class.java)
    private val appRepo = LocalRepoManager.load(AppData::class.java)

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            val data = api.userLogin(phone, password)
            _msg.value = data.message

            if (data.result == 1) {
                appRepo.isLogin = true
                appRepo.lastLoginPhone = phone
                appRepo.apply()
            }
        }
    }

    fun test(value: Int) {
        viewModelScope.launch {
            val v = api.test(value).data
            if (v == null){
                _msg.value = "Null Data"
            }else{
                _msg.value = v.code.toString()
            }
        }
    }

    fun isGoHome(): Boolean {
        return appRepo.isLogin
    }
}