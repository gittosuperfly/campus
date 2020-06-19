package com.cai.campus.features.group.signIn.census

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.SignInApiServer
import com.cai.campus.common.network.model.SignIn
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class SignInListViewModel : ViewModel() {

    private val localStorage = LocalRepoManager.load(AppData::class.java)
    private val api = RetrofitFactory.instance.getService(SignInApiServer::class.java)

    private val _listData = MutableLiveData<List<SignIn>>()
    val listData: LiveData<List<SignIn>> = _listData

    fun getSignInList(groupId: Int) {
        viewModelScope.launch {
            val response = api.queryAllSignIn(groupId)
            _listData.value = response.data
            Prompt.show(response.message)
        }
    }
}