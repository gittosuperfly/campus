package com.cai.campus.features.group.signIn.census

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.features.group.adapter.SignInRecyclerAdapter
import kotlinx.android.synthetic.main.sign_in_list_activity.*

@Route(path = RouterPath.SIGN_IN_LIST)
class SignInListActivity : BaseActivity() {

    private lateinit var viewModel: SignInListViewModel
    private lateinit var thisGroup: GroupAccount
    private lateinit var adapter: SignInRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.sign_in_list_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(SignInListViewModel::class.java)
        thisGroup = intent.getSerializableExtra(ExtraKey.GROUP_DETAIL_VALUE) as GroupAccount
        viewModel.getSignInList(thisGroup.groupId)

        adapter = SignInRecyclerAdapter(listOf())
        signInListRv.layoutManager = LinearLayoutManager(this)
        signInListRv.adapter = adapter

        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun subscribeOnView() {
        viewModel.listData.observe(this, Observer {
            val list = it.reversed()
            adapter.refresh(list)
            adapter.setOnItemClickListner { _, position ->
                ARouter.getInstance()
                    .build(RouterPath.SIGN_IN_DETAIL)
                    .withSerializable(ExtraKey.SIGN_IN_DETAIL_VALUE, list[position])
                    .navigation()
            }
        })
    }
}