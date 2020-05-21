package com.cai.campus.features.main

import android.Manifest
import android.annotation.SuppressLint
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
import com.cai.campus.common.router.RequestCode
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import com.cai.campus.features.group.GroupFragment
import com.cai.campus.features.home.HomeFragment
import com.cai.campus.features.message.MessageFragment
import com.cai.campus.features.user.UserFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.main_activity.*

@Route(path = RouterPath.HOME_PAGE)
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.main_activity)
    }

    @SuppressLint("ResourceType")
    override fun init() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainNavView.itemIconTintList =
            resources.getColorStateList(R.drawable.selector_main_button, null)
        mainNavView.itemTextColor =
            resources.getColorStateList(R.drawable.selector_main_button, null)

        Glide.with(this)
            .load("http://www.xhbxw.com/images/201908/f2hci1cw2cx.jpg")
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

    }

    private fun bindNavAndViewPager() {
        mainViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 4
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> MessageFragment()
                    2 -> GroupFragment()
                    3 -> UserFragment()
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
                R.id.home_nav_ic1 -> mainViewPager.setCurrentItem(0, true)
                R.id.home_nav_ic2 -> mainViewPager.setCurrentItem(1, true)
                R.id.home_nav_ic3 -> mainViewPager.setCurrentItem(2, true)
                R.id.home_nav_ic4 -> mainViewPager.setCurrentItem(3, true)
            }
            true
        }
    }
}
