package com.cai.campus.features.group.detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.code.CreateQRCodeUtil
import com.cai.campus.common.code.model.QRCodeInfo
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.GroupApiServer
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.network.model.GroupUser
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import com.google.gson.Gson
import kotlinx.coroutines.launch

class GroupDetailViewModel : ViewModel() {

    val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(GroupApiServer::class.java)

    private val _thisGroup = MutableLiveData<GroupAccount>()
    val thisGroup: LiveData<GroupAccount> = _thisGroup

    private val _userList = MutableLiveData<List<GroupUser>>()
    val userList: LiveData<List<GroupUser>> = _userList

    private val _userStatus = MutableLiveData<Int>()
    val userStatus: LiveData<Int> = _userStatus

    private val _isFinish = MutableLiveData(false)
    val isFinish: LiveData<Boolean> = _isFinish

    private val _isClick = MutableLiveData(false)
    val isClick: LiveData<Boolean> = _isClick

    var me: UserAccount

    init {
        me = localStorage.lastLoginUser
    }

    fun getUserList(groupId: Int) {
        viewModelScope.launch {
            val dataList = api.queryGroupAllUser(groupId).data.sortedByDescending {
                it.status
            }
            for (groupUser in dataList) {
                if (groupUser.userInfo.uid == localStorage.lastLoginUser.uid) {
                    _userStatus.value = groupUser.status
                }
            }
            _userList.value = dataList
            _isClick.value = true
        }
    }

    fun getGroupQRCode(groupId: Int): Bitmap {
        return CreateQRCodeUtil.create(
            Gson().toJson(
                QRCodeInfo(
                    QRCodeInfo.TYPE_ADD_GROUP, groupId
                )
            ), 800, 800
        )!!
    }

    fun quitGroup(
        groupId: Int,
        userId: Int? = localStorage.lastLoginUser.uid!!
    ) {
        viewModelScope.launch {
            if (userId == me.uid) {
                if (_userStatus.value == 2) {
                    Prompt.show("您是此群群主，无法退群，请转让群主后再此尝试")
                } else {
                    Prompt.show(api.quitGroup(userId!!, groupId).message)
                    _isFinish.value = true
                }
            } else {
                api.quitGroup(userId!!, groupId).message
                Prompt.show("操作成功")
                getUserList(userId)
            }
        }
    }

    fun setAdmin(groupId: Int, userId: Int, status: Int) {
        viewModelScope.launch {
            Prompt.show(api.setGroupAdmin(groupId, userId, status).message)
            getUserList(groupId)
        }
    }

    fun transferGroup(groupId: Int, userId: Int) {
        viewModelScope.launch {
            Prompt.show(api.transferGroup(groupId, me.uid!!, userId).message)
            getUserList(groupId)
        }
    }

    fun deleteGroup(groupId: Int) {
        viewModelScope.launch {
            Prompt.show(api.deleteGroup(groupId).message)
            _isFinish.value = true
        }
    }

    fun updateGroup(groupId: Int, name: String) {
        viewModelScope.launch {
            val response = api.updateGroup(groupId = groupId, value = name)
            if (response.result == 1){
                Prompt.show(response.message)
                _thisGroup.value = response.data
            }
        }
    }

    fun setThisGroup(group:GroupAccount){
        _thisGroup.value = group
    }
}