package com.cai.campus.features.user

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.UserAccount
import com.cai.campus.common.router.RouterPath
import kotlinx.android.synthetic.main.user_update_activity.*


@Route(path = RouterPath.UPDATE_USER)
class UserUpdateActivity : BaseActivity() {

    private lateinit var viewModel: UserUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.user_update_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(UserUpdateViewModel::class.java)

//        val pvTime =
//            TimePickerBuilder(this@UserUpdateActivity,
//                OnTimeSelectListener { date, v ->
//                    Toast.makeText(this@UserUpdateActivity, date.time.toString(), Toast.LENGTH_SHORT)
//                        .show()
//                }).build()
//
//        timeSelectIv.setOnClickListener {
//            pvTime.show()
//        }


        backBtn.setOnClickListener {
            finish()
        }

        userNameEdit.setText(viewModel.getUser().name)
        userEmailEdit.setText(viewModel.getUser().email)

        timeSelectIv.visibility = View.INVISIBLE

        submitBtn.setOnClickListener {
            viewModel.updateUserInfo(userNameEdit.text.toString(), userEmailEdit.text.toString())
        }


    }

    override fun subscribeOnView() {

    }
}
