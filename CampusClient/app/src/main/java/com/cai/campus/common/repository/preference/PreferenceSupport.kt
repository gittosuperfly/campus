package com.cai.campus.common.repository.preference

import com.cai.campus.common.repository.LocalRepository

abstract class PreferenceSupport {

    fun apply() {
        // 将当前类中的修改。同步到sp中去(任务运行于子线程)
        LocalRepository.find(javaClass)
            .apply()
    }

    fun commit() {
        LocalRepository.find(javaClass)
            .commit()
    }
}