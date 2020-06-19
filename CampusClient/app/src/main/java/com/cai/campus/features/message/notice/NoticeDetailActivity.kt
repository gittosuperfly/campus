package com.cai.campus.features.message.notice

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.GroupMessage
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Change
import kotlinx.android.synthetic.main.activity_notice_detail.*
import kotlinx.android.synthetic.main.sign_in_list_activity.*

@Route(path = RouterPath.NOTICE_DETAIL)
class NoticeDetailActivity : BaseActivity() {

    private lateinit var viewModel: NoticeDetailViewModel
    private lateinit var message: GroupMessage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_notice_detail)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(NoticeDetailViewModel::class.java)
        message = intent.getSerializableExtra(ExtraKey.GROUP_MESSAGE) as GroupMessage


        messageTv.text = message.notice!!.notice
        sendUserTv.text = "发件人：${message.createUserName}"
        sendGroupTv.text = "送达群组：${message.groupName}"
        sendTimeTv.text = "发送时间：${Change.timestampToTime(message.notice!!.releaseTime.toString())}"

        deleteBtn.setOnClickListener {
            val builder =
                AlertDialog.Builder(this).setMessage("您确定要删除此条消息吗?")
                    .setPositiveButton(
                        "确定"
                    ) { _, _ -> //按下确定键后的事件
                        viewModel.deleteMessage(message.notice!!.id!!)
                    }.setNegativeButton("取消", null)

            val dialog = builder.create()
            dialog.show()
            dialogCenter(dialog)
        }

    }

    override fun subscribeOnView() {
        viewModel.isFinish.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }

    private fun dialogCenter(dialog: AlertDialog) {
        val dialogWindow = dialog.window
        val m = this.windowManager
        val d = m.defaultDisplay
        val p = dialogWindow!!.attributes
        p.width = (d.width * 0.95).toInt()
        p.gravity = Gravity.CENTER
        dialogWindow.attributes = p
    }
}