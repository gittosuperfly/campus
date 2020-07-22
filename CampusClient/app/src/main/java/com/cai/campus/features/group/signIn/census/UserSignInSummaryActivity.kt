package com.cai.campus.features.group.signIn.census

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.features.group.adapter.UserSignInSummaryAdapter
import kotlinx.android.synthetic.main.activity_user_sign_in_summary.*

@Route(path = RouterPath.USER_SIGN_IN_SUMMARY)
class UserSignInSummaryActivity : BaseActivity() {

    private lateinit var viewModel: UserSignInSummaryViewModel
    private lateinit var thisUser: UserAccount
    private lateinit var adapter: UserSignInSummaryAdapter
    private var groupId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_user_sign_in_summary)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(UserSignInSummaryViewModel::class.java)
        thisUser = intent.getSerializableExtra(ExtraKey.USER_INFO) as UserAccount
        groupId = intent.getIntExtra(ExtraKey.GROUP_ID, 0)

        adapter = UserSignInSummaryAdapter(listOf())

        userCensusRv.layoutManager = LinearLayoutManager(this)
        userCensusRv.adapter = adapter

        viewModel.getUserSignInRecord(thisUser.uid!!, groupId!!)


        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun subscribeOnView() {
        viewModel.listData.observe(this, Observer {
            adapter.refresh(it)
        })
    }
}