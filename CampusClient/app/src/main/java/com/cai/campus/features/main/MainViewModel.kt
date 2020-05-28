package com.cai.campus.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.GroupApiServer
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(GroupApiServer::class.java)

    fun addGroup(groupId: Int) {
        viewModelScope.launch {
            val response = localStorage.lastLoginUser.uid?.let { api.addGroup(it, groupId) }
            Prompt.show(response!!.message)
        }
    }
}