package com.cai.campus.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.LoginApiServer
import com.cai.campus.common.network.model.Response
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(LoginApiServer::class.java)
    private val appRepo = LocalRepoManager.load(AppData::class.java)

    private val _apiData = MutableLiveData<Response<String?>>()
    val apiData: LiveData<Response<String?>> = _apiData


}