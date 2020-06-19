package com.cai.campus.features.group.signIn.census

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.SignInApiServer
import com.cai.campus.common.network.model.GroupSignInRecord
import com.cai.campus.common.network.model.UserSignInDetail
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import com.google.gson.Gson
import kotlinx.coroutines.launch

class UserSignInSummaryViewModel : ViewModel() {

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(SignInApiServer::class.java)

    private val _listData = MutableLiveData<List<UserSignInDetail>>()
    val listData: LiveData<List<UserSignInDetail>> = _listData

    fun getUserSignInRecord(uid: Int, groupId: Int) {
        viewModelScope.launch {
            val dataList = api.queryUserAllRecordList(uid).data.filter {
                it.signIn!!.groupId == groupId
            }
            _listData.value = dataList
        }
    }

}