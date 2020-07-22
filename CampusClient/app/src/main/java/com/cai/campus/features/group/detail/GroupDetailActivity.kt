package com.cai.campus.features.group.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import com.cai.campus.features.group.adapter.UserGridRecyclerAdapter
import com.cai.campus.features.group.dialog.BottomEnumDialog
import com.cai.campus.features.group.dialog.CodeImageDialog
import com.cai.campus.features.group.dialog.LookUserInfoDialog
import kotlinx.android.synthetic.main.activity_group_detail.*


@Route(path = RouterPath.GROUP_DETAIL)
@SuppressLint("SetTextI18n")
class GroupDetailActivity : BaseActivity() {

    private lateinit var viewModel: GroupDetailViewModel
    private lateinit var thisGroup: GroupAccount

    private lateinit var userGridListAdapter: UserGridRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_group_detail)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(GroupDetailViewModel::class.java)
        thisGroup = intent.getSerializableExtra(ExtraKey.GROUP_DETAIL_VALUE) as GroupAccount
        viewModel.setThisGroup(thisGroup)

        viewModel.getUserList(thisGroup.groupId)
        userGridListAdapter = UserGridRecyclerAdapter(listOf())
        userListView.layoutManager = GridLayoutManager(this, 5)
        userListView.adapter = userGridListAdapter

        backBtn.setOnClickListener {
            finish()
        }

        groupIdBtn.setOnClickListener {
            CodeImageDialog(this, viewModel.getGroupQRCode(thisGroup.groupId)).show()
        }

        releaseSignInBtn.clickListener {
            if (viewModel.userStatus.value!! == 0) {
                Prompt.show("您暂无发起签到权限")
            } else {
                ARouter.getInstance()
                    .build(RouterPath.RELEASE_SIGN_IN)
                    .withSerializable(ExtraKey.GROUP_DETAIL_VALUE, thisGroup)
                    .navigation()
            }
        }

        querySignInBtn.clickListener {
            if (viewModel.userStatus.value!! == 0) {
                Prompt.show("您暂无查看权限")
            } else {
                ARouter.getInstance()
                    .build(RouterPath.SIGN_IN_LIST)
                    .withSerializable(ExtraKey.GROUP_DETAIL_VALUE, thisGroup)
                    .navigation()
            }
        }
        updateGroupBtn.clickListener {
            if (viewModel.userStatus.value!! == 0) {
                Prompt.show("您暂无修改权限")
            } else {
                val content: View =
                    LayoutInflater.from(this).inflate(R.layout.edit_dialog_layout, null)
                val edit = content.findViewById<EditText>(R.id.dialogEdit)
                edit.hint = "请输入新的群名称"
                val bundle =
                    AlertDialog.Builder(this)
                        .setTitle("修改群名称")
                        .setView(content).setPositiveButton(
                            "确定"
                        ) { _, _ -> //按下确定键后的事件
                            viewModel.updateGroup(thisGroup.groupId, edit.text.toString())
                        }.setNegativeButton("取消", null)

                val dialog = bundle.create()
                dialog.show()
                dialogCenter(dialog)
            }
        }

        deleteGroupBtn.clickListener {
            if (viewModel.userStatus.value!! != 2) {
                Prompt.show("您暂无权限解散该群")
            } else {
                val builder = AlertDialog.Builder(this).setTitle("⚠请注意")
                    .setMessage("您确定要解散群" + thisGroup.name + "吗？")
                    .setPositiveButton(
                        "确定"
                    ) { _, _ -> //按下确定键后的事件
                        viewModel.deleteGroup(thisGroup.groupId)
                    }.setNegativeButton("取消", null)
                val dialog = builder.create()
                dialog.show()
                dialogCenter(dialog)
            }
        }

        sendMessageBtn.clickListener {
            if (viewModel.userStatus.value!! == 0) {
                Prompt.show("您暂无通知权限")
            } else {
                ARouter.getInstance()
                    .build(RouterPath.SEND_NOTICE)
                    .withSerializable(ExtraKey.GROUP_DETAIL_VALUE, thisGroup)
                    .navigation()
            }
        }


        quitGroupBtn.clickListener {

            val builder =
                AlertDialog.Builder(this).setMessage("您确定要退出群" + thisGroup.name + "吗?")
                    .setPositiveButton(
                        "确定"
                    ) { _, _ -> //按下确定键后的事件
                        viewModel.quitGroup(thisGroup.groupId)
                    }.setNegativeButton("取消", null)

            val dialog = builder.create()
            dialog.show()
            dialogCenter(dialog)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserList(thisGroup.groupId)
    }

    override fun subscribeOnView() {

        viewModel.thisGroup.observe(this, Observer {
            groupNameTv.text = it.name
            groupIdTv.text = "群组ID: ${it.groupId}"
        })

        viewModel.userList.observe(this, Observer {

            userGridListAdapter.refresh(it)

            userGridListAdapter.setOnItemClickListner { _, position ->
                object : BottomEnumDialog(
                    this,
                    viewModel.userStatus.value!!,
                    viewModel.me,
                    it[position]
                ) {
                    override fun lookUserBtnClick() {
                        LookUserInfoDialog(this@GroupDetailActivity, it[position].userInfo).show()
                    }

                    override fun setAdminBtnClick() {
                        viewModel.setAdmin(
                            thisGroup.groupId,
                            it[position].userInfo.uid!!
                            , if (it[position].status == 1) 0 else 1
                        )
                    }

                    override fun makeOverBtnClick() {
                        val builder = AlertDialog.Builder(this@GroupDetailActivity)
                            .setTitle("是否将群" + thisGroup.name + "转让给" + it[position].userInfo.name + "?")
                            .setPositiveButton(
                                "确定"
                            ) { _, _ -> //按下确定键后的事件
                                viewModel.transferGroup(
                                    thisGroup.groupId,
                                    it[position].userInfo.uid!!
                                )
                            }.setNegativeButton("取消", null)
                        val dialog = builder.create()
                        dialog.show()
                        dialogCenter(dialog)
                    }

                    override fun quitUserBtnClick() {
                        viewModel.quitGroup(thisGroup.groupId, it[position].userInfo.uid)
                        viewModel.getUserList(thisGroup.groupId)
                    }

                    override fun lookSignInBtnClick() {
                        ARouter.getInstance()
                            .build(RouterPath.USER_SIGN_IN_SUMMARY)
                            .withSerializable(ExtraKey.USER_INFO, it[position].userInfo)
                            .withInt(ExtraKey.GROUP_ID, thisGroup.groupId)
                            .navigation()
                    }
                }.show()
            }
        })

        viewModel.isFinish.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }

    private fun dialogCenter(dialog: AlertDialog) {
        val dialogWindow = dialog.window
        val m = windowManager
        val d = m.defaultDisplay
        val p = dialogWindow!!.attributes
        p.width = (d.width * 0.95).toInt()
        p.gravity = Gravity.CENTER
        dialogWindow.attributes = p
    }

    private fun View.clickListener(function: () -> Unit) {
        setOnClickListener {
            if (viewModel.isClick.value!!) {
                function()
            } else {
                Prompt.show("请稍等...")
            }
        }
    }

}
