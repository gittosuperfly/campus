package com.cai.campus.features.message.signIn

import android.app.VoiceInteractor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.SignInApiServer
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.network.model.SignInRecord
import com.cai.campus.common.network.model.UserSignInDetail
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Location
import com.cai.campus.common.utils.Prompt
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*

class UserSignInViewModel : ViewModel() {

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(SignInApiServer::class.java)

    private val _isFinish = MutableLiveData<Boolean>(false)
    val isFinish: LiveData<Boolean> = _isFinish


    fun userSignIn(record: SignInRecord) {
        viewModelScope.launch {
            val response = api.goSignIn(
                signInId = record.signId!!,
                recordId = record.id!!,
                location = Gson().toJson(localStorage.location),
                UUID = localStorage.phoneId
            )
            Prompt.show(response.message)
            _isFinish.value = true
        }
    }
}