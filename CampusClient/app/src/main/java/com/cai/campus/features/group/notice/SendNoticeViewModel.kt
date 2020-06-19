package com.cai.campus.features.group.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cai.campus.common.network.RetrofitFactory
import com.cai.campus.common.network.api.NoticeApiServer
import com.cai.campus.common.repository.LocalRepoManager
import com.cai.campus.common.repository.repo.AppData
import com.cai.campus.common.utils.Prompt
import kotlinx.coroutines.launch

class SendNoticeViewModel : ViewModel() {

    private val api = RetrofitFactory.instance.getService(NoticeApiServer::class.java)
    val localStorage = LocalRepoManager.load(AppData::class.java)

    fun releaseNotice(groupId: Int, detail: String) {
        viewModelScope.launch {
            Prompt.show(
                api.releaseNotice(
                    uid = localStorage.lastLoginUser.uid!!,
                    groupId = groupId,
                    releaseTime = System.currentTimeMillis() / 1000,
                    detail = detail
                ).message
            )
        }
    }


}