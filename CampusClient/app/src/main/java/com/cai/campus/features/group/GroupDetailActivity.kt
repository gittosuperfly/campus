package com.cai.campus.features.group

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.code.CreateQRCodeUtil
import com.cai.campus.common.code.model.QRCodeInfo
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.repository.globalValue.DefaultLogo
import com.cai.campus.common.router.RouterPath
import com.cai.campus.features.group.adapter.UserGridRecyclerAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.group_detail_activity.*


@Route(path = RouterPath.GROUP_DETAIL)
class GroupDetailActivity : BaseActivity() {

    private lateinit var viewModel: GroupDetailViewModel
    private lateinit var thisGroup: GroupAccount

    private lateinit var userGridListAdapter: UserGridRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.group_detail_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(GroupDetailViewModel::class.java)
        thisGroup = intent.getSerializableExtra("group") as GroupAccount
        Glide.with(this)
            .load(DefaultLogo.circleUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(groupLogo)
        groupNameTv.text = thisGroup.name
        groupIdTv.text = "群组ID: ${thisGroup.groupId}"


        viewModel.getUserList(thisGroup.groupId!!)
        userGridListAdapter = UserGridRecyclerAdapter(listOf())
        userListView.layoutManager = GridLayoutManager(this, 5)
        userListView.adapter = userGridListAdapter

        Glide.with(this)
            .load(
                CreateQRCodeUtil.create(
                    Gson().toJson(
                        QRCodeInfo(
                            QRCodeInfo.TYPE_ADD_GROUP,
                            thisGroup.groupId
                        )
                    ), 200, 200
                )
            )
            .into(codeImage)

    }

    override fun subscribeOnView() {
        viewModel.userList.observe(this, Observer {
            userGridListAdapter.refresh(it)
        })
    }
}
