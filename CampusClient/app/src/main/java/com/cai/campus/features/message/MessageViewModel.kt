package com.cai.campus.features.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.NoticeApiServer
import com.cai.campus.common.network.api.SignInApiServer
import com.cai.campus.common.network.model.GroupMessage
import com.cai.campus.common.network.model.GroupNotice
import com.cai.campus.common.network.model.UserSignInDetail
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(SignInApiServer::class.java)
    private val msgApi = RetrofitFactory.instance.getService(NoticeApiServer::class.java)


    private val _signInListData = MutableLiveData<List<UserSignInDetail>>()
    val signInListData: LiveData<List<UserSignInDetail>> = _signInListData

    private val _messageListData = MutableLiveData<List<GroupMessage>>()
    val messageListData: LiveData<List<GroupMessage>> = _messageListData

    init {
        getUserSignInList()
        getUserMessageList()
    }

    fun getUserSignInList() {
        viewModelScope.launch {
            _signInListData.value =
                api.queryUserAllRecordList(localStorage.lastLoginUser.uid!!).data
        }
    }

    fun getUserMessageList() {
        viewModelScope.launch {
            _messageListData.value = msgApi.queryByUser(localStorage.lastLoginUser.uid!!).data
        }
    }


}
