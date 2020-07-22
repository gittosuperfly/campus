package com.cai.campus.features.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.code.model.QRCodeInfo
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RequestCode
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Check
import com.cai.campus.common.utils.Prompt
import com.cai.campus.features.group.GroupFragment
import com.cai.campus.features.home.HomeFragment
import com.cai.campus.features.message.MessageFragment
import com.cai.campus.features.user.UserFragment
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.HOME_PAGE)
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_main)
    }

    @SuppressLint("ResourceType")
    override fun init() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (viewModel.isGoLoginPage()) {
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
            finish()
        } else {

            mainNavView.itemIconTintList =
                resources.getColorStateList(R.drawable.selector_main_button, null)
            mainNavView.itemTextColor =
                resources.getColorStateList(R.drawable.selector_main_button, null)

            Glide.with(this)
                .load(
                    getDrawable(
                        if (viewModel.localStorage.lastLoginUser.sex == 1) R.drawable.ic_boy_logo
                        else R.drawable.ic_girl_logo
                    )
                )
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(mainTopBarLogo)


            bindNavAndViewPager()

            mainTopScan.setOnClickListener {
                RxPermissions(this).request(Manifest.permission.CAMERA).subscribe {
                    if (it) {
                        ARouter.getInstance().build(RouterPath.SCAN_QR_CODE)
                            .navigation(this, RequestCode.SCAN_QR_CODE)
                    } else {
                        Prompt.show("请给我相机权限")
                    }
                }
            }

            mainViewPager.isUserInputEnabled = false

            RxPermissions(this).request(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.VIBRATE,
                Manifest.permission.GET_TASKS,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            ).subscribe {
                if (it) {

                } else {
                    Prompt.show("请给我相应的权限")
                    finish()
                }
            }
        }
    }

    private fun bindNavAndViewPager() {
        mainViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> MessageFragment()
                    1 -> GroupFragment()
                    2 -> UserFragment()
                    else -> HomeFragment()
                }
            }
        }

        // 当ViewPager切换页面时，改变底部导航栏的状态
        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mainNavView.menu.getItem(position).isChecked = true
                mainTopBarTitle.text = mainNavView.menu.getItem(position).title
            }
        })


        // 当ViewPager切换页面时，改变ViewPager的显示
        mainNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav_ic2 -> mainViewPager.setCurrentItem(0, true)
                R.id.home_nav_ic3 -> mainViewPager.setCurrentItem(1, true)
                R.id.home_nav_ic4 -> mainViewPager.setCurrentItem(2, true)
            }
            true
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCode.SCAN_QR_CODE && data != null) {
            val value = data.getStringExtra(ExtraKey.QR_REQUEST_VALUE)!!
            if (Check.isOkQRCode(value)) {
                val codeInfo = Gson().fromJson(value, QRCodeInfo::class.java)
                if (codeInfo.handlerType == QRCodeInfo.TYPE_ADD_GROUP) {
                    viewModel.addGroup(codeInfo.value)
                }
            } else {
                Prompt.show("您扫描的内容为: $value")
            }

        }
    }
}
