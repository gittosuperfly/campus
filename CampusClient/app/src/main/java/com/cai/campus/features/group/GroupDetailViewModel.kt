package com.cai.campus.features.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.GroupApiServer
import com.cai.campus.common.network.model.GroupUser
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class GroupDetailViewModel : ViewModel() {

    val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(GroupApiServer::class.java)

    private val _userList = MutableLiveData<List<GroupUser>>()
    val userList: LiveData<List<GroupUser>> = _userList

    fun getUserList(groupId: Int) {
        viewModelScope.launch {
            val dataList = api.queryGroupAllUser(groupId).data.sortedByDescending {
                it.status
            }
            _userList.value = dataList
        }
    }
}