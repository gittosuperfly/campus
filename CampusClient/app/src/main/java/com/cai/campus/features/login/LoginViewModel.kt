package com.cai.campus.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.LoginApiServer
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg


    fun push() {
        viewModelScope.launch {
            val str = RetrofitFactory.instance.getService(LoginApiServer::class.java).pushTest()
            _msg.value = str.message
        }
    }
}