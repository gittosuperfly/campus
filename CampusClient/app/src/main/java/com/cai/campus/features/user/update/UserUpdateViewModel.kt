package com.cai.campus.features.user.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.UserApiServer
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class UserUpdateViewModel : ViewModel() {
    private val api = RetrofitFactory.instance.getService(UserApiServer::class.java)
    private val localStorage = LocalRepoManager.load(AppData::class.java)

    fun updateUserInfo(
        userName: String,
        email: String,
        sex: Int,
        intro: String,
        file: File? = null
    ) {
        val userInfo = localStorage.lastLoginUser

        userInfo.name = userName
        userInfo.email = email
        userInfo.sex = sex
        userInfo.introduction = intro

        viewModelScope.launch {
            val response = api.updateUser(userInfo)
            Prompt.show(response.message)
            if (response.result == 1) {
                localStorage.lastLoginUser = userInfo
            }

            if (file != null) {

                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("form-data"), file)
                var encodeName: String? = null
                try {
                    encodeName = URLEncoder.encode(file.name, "UTF-8")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
                val body = MultipartBody.Part.createFormData("file", encodeName, requestFile)
                api.updateUserLogo(uid = userInfo.uid!!, file = body)
            }
        }
    }

    fun getUser(): UserAccount {
        return localStorage.lastLoginUser
    }
}