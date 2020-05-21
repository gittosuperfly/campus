package com.cai.campus.features.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath

@Route(path = RouterPath.CREATE_GROUP)
class CreateGroupActivity : BaseActivity() {

    private lateinit var viewModel: CreateGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_group_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(CreateGroupViewModel::class.java)

    }
}
