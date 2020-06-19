package com.cai.campus.features.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.GroupApiServer
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.push.PushManager
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import com.mob.pushsdk.MobPush
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(GroupApiServer::class.java)

    private val _groupList = MutableLiveData<List<GroupAccount>>()
    val groupList: LiveData<List<GroupAccount>> = _groupList

    init {
        getAllGroup()
    }

    fun getAllGroup() {
        viewModelScope.launch {
            val groupList = localStorage.lastLoginUser.uid?.let { api.queryUserAllGroup(it).data }
            _groupList.value = groupList
            if (groupList != null) {
                val tags = arrayOfNulls<String>(groupList.size)
                for (i in groupList.indices) {
                    tags[i] = groupList[i].groupId.toString()
                }
                MobPush.cleanTags()
                PushManager.addGroup(tags)
            }
        }
    }

    fun createGroup(groupName: String) {
        viewModelScope.launch {
            val response = localStorage.lastLoginUser.uid?.let { api.createGroup(groupName, it) }
            Prompt.show(response!!.message)
            getAllGroup()
        }
    }

    fun addGroup(groupId: Int) {
        viewModelScope.launch {
            val response = localStorage.lastLoginUser.uid?.let { api.addGroup(it, groupId) }
            Prompt.show(response!!.message)
            getAllGroup()
        }
    }
}
