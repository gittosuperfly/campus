package com.cai.campus.features.group.signIn.census

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.network.model.SignIn
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.features.group.adapter.SignInStatisticsRecyclerAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.sign_in_detail_activity.*


@Route(path = RouterPath.SIGN_IN_DETAIL)
class SignInDetailActivity : BaseActivity() {

    private lateinit var viewModel: SignInDetailViewModel
    private lateinit var signIn: SignIn
    private lateinit var adapter: SignInStatisticsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.sign_in_detail_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(SignInDetailViewModel::class.java)
        signIn = intent.getSerializableExtra(ExtraKey.SIGN_IN_DETAIL_VALUE) as SignIn
        viewModel.getSignInRecord(signIn.signId!!)

        adapter = SignInStatisticsRecyclerAdapter(listOf())
        val adminLocation = Gson().fromJson(signIn.location, LocationBean::class.java)
        adapter.adminLocation = adminLocation
        signInRecordRv.layoutManager = LinearLayoutManager(this)
        signInRecordRv.adapter = adapter

        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun subscribeOnView() {
        viewModel.listData.observe(this, Observer {
            adapter.refresh(it)
            var i = 0
            for (record in it) {
                if (record.record.isDone == 1) {
                    i++
                }
            }
            signInCensusTv.text = "本次签到共${it.size}人，已签到${i}人，${it.size - i}人未签到"
        })
    }
}