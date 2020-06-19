package com.cai.campus.features.group.signIn.release

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.SignInApiServer
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Location
import com.cai.campus.common.utils.Prompt
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ReleaseSignViewModel : ViewModel() {

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(SignInApiServer::class.java)

    private val _isFinish = MutableLiveData(false)
    val isFinish: LiveData<Boolean> = _isFinish

    fun releaseSignIn(groupId: Int, time: Int, laterTime: Int, detail: String) {

        viewModelScope.launch {
            val response = api.releaseSignIn(
                uid = localStorage.lastLoginUser.uid!!,
                groupId = groupId,
                createTime = getCurrentTimeStamp().toLong() + laterTime * 60,
                endTime = getCurrentTimeStamp().toLong() + (time + laterTime) * 60,
                location = Gson().toJson(localStorage.location),
                detail = detail
            )
            Prompt.show(response.message)
            _isFinish.value = true
        }
    }

    private fun getCurrentTimeStamp(): String {
        var time = System.currentTimeMillis()
        return (time / 1000).toString()
    }
}