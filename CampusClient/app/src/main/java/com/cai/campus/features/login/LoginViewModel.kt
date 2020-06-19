package com.cai.campus.features.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.push.PushManager
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    private val _apiData = MutableLiveData<Response<String?>>()
    val apiData: LiveData<Response<String?>> = _apiData

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            val data = api.userLogin(phone, password)
            Prompt.show(data.message)
            if (data.result == 1) {
                //登录成功后存储用户信息
                val response = api.queryUser("phone", phone)
                localStorage.isLogin = true
                localStorage.lastLoginUser = response.data!!
                localStorage.apply()
                //设置推送Phone
                PushManager.setPhone(phone)
                _apiData.value = data
            }
        }
    }
}