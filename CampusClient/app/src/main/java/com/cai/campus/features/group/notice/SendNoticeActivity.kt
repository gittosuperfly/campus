package com.cai.campus.features.group.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.activity_user_sign_in_summary.*
import kotlinx.android.synthetic.main.send_notice_activity.*
import kotlinx.android.synthetic.main.send_notice_activity.backBtn

@Route(path = RouterPath.SEND_NOTICE)
class SendNoticeActivity : BaseActivity() {

    private lateinit var viewModel: SendNoticeViewModel
    private lateinit var thisGroup: GroupAccount


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.send_notice_activity)

        backBtn.setOnClickListener {
            finish()
        }
    }


    override fun init() {
        viewModel = ViewModelProvider(this).get(SendNoticeViewModel::class.java)
        thisGroup = intent.getSerializableExtra(ExtraKey.GROUP_DETAIL_VALUE) as GroupAccount

        submitBtn.setOnClickListener {
            val notice = noticeEdit.text.toString()
            if (notice.isNotEmpty()) {
                viewModel.releaseNotice(thisGroup.groupId, noticeEdit.text.toString())
            } else {
                Prompt.show("请输入内容")
            }
        }

    }


}