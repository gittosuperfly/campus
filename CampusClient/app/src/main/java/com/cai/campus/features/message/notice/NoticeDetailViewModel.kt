package com.cai.campus.features.message.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.NoticeApiServer
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class NoticeDetailViewModel : ViewModel() {
    private val api = RetrofitFactory.instance.getService(NoticeApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    private val _isFinish = MutableLiveData(false)
    val isFinish: LiveData<Boolean> = _isFinish

    fun deleteMessage(id: Int) {
        viewModelScope.launch {
            Prompt.show(api.delete(localStorage.lastLoginUser.uid!!, id).message)
            _isFinish.value = true
        }
    }
}