package com.cai.campus.common.repository.preference

import com.cai.campus.common.repository.LocalRepoManager

abstract class PreferenceSupport {

    fun apply() {
        // 将当前类中的修改。同步到sp中去(任务运行于子线程)
        LocalRepoManager.find(javaClass)
            .apply()
    }

    fun commit() {
        LocalRepoManager.find(javaClass)
            .commit()
    }
}