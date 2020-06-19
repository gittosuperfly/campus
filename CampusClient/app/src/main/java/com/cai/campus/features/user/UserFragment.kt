package com.cai.campus.features.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.amap.api.maps2d.AMapUtils
import com.bumptech.glide.Glide
import com.cai.campus.R
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateUserInfo()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // TODO: Use the ViewModel

        updateUserBtn.setOnClickListener {
            ARouter.getInstance().build(RouterPath.UPDATE_USER).navigation()
        }

        quitLoginBtn.setOnClickListener {
            viewModel.quitLogin()
            Prompt.show("用户已退出登录")
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
            activity!!.finish()
        }

        shareBtn.setOnClickListener {
            var shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi~快来和我一起使用Team校园吧，我在这里等你~ 下载地址: 敬请期待")
            shareIntent = Intent.createChooser(shareIntent, "向好友分享Team校园")
            startActivity(shareIntent)
        }

        viewModel.user.observe(this.viewLifecycleOwner, Observer {
            userName.text = it.name
            userPhone.text = it.phone
            userEmail.text = if (it.email == null) "暂无" else it.email
            userIntro.text = if (it.introduction.isNullOrEmpty()) "暂无简介" else it.introduction
        })
    }

}
