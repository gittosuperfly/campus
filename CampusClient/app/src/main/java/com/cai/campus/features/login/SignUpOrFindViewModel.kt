package com.cai.campus.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import kotlinx.coroutines.launch

class SignUpOrFindViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    private val _msg = MutableLiveData("")
    val msg: LiveData<String> = _msg

    private val _isFinish = MutableLiveData(false)
    val isFinish: LiveData<Boolean> = _isFinish


    fun registerUser(phone: String, password: String) {
        viewModelScope.launch {
            val data = api.userRegister(phone, password)
            _msg.value = data.message
            if (data.result == 1) {
                _isFinish.value = true
            }
        }
    }

    fun resetPassword(phone: String, password: String) {
        viewModelScope.launch {
            val data = api.resetPassword(phone, password)
            _msg.value = data.message
            if (data.result == 1) {
                _isFinish.value = true
            }
        }
    }
}
