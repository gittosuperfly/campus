package com.cai.campus.features.group.signIn.release

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.release_sign_in_activity.*

@Route(path = RouterPath.RELEASE_SIGN_IN)
class ReleaseSignInActivity : BaseActivity() {


    private lateinit var viewModel: ReleaseSignViewModel
    private lateinit var thisGroup: GroupAccount

    private var isLater = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.release_sign_in_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(ReleaseSignViewModel::class.java)
        thisGroup = intent.getSerializableExtra(ExtraKey.GROUP_DETAIL_VALUE) as GroupAccount

        backBtn.setOnClickListener {
            finish()
        }

        isLaterBtn.setOnClickListener {
            isLater = !isLater
            if (isLater) {
                laterTimeEdit.visibility = View.VISIBLE
                isLaterBtn.text = "取消延后"
            } else {
                laterTimeEdit.visibility = View.GONE
                isLaterBtn.text = "延后开始"
            }
        }

        submitBtn.setOnClickListener {
            if (timeEdit.text.toString().isEmpty()) {
                Prompt.show("请输入持续时长")
                return@setOnClickListener
            }
            if (isLater && laterTimeEdit.text.toString().isEmpty()) {
                Prompt.show("请输入延后时长")
                return@setOnClickListener
            }

            val time = timeEdit.text.toString().toInt()
            val laterTime = if (isLater) laterTimeEdit.text.toString().toInt() else 0
            val detail = detailEdit.text.toString()
            viewModel.releaseSignIn(thisGroup.groupId, time, laterTime, detail)
        }

    }

    override fun subscribeOnView() {
        viewModel.isFinish.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }
}