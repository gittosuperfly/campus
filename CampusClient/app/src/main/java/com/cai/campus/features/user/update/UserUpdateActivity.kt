package com.cai.campus.features.user.update

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import kotlinx.android.synthetic.main.activity_user_update.*


@Route(path = RouterPath.UPDATE_USER)
class UserUpdateActivity : BaseActivity() {

    private lateinit var viewModel: UserUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_user_update)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(UserUpdateViewModel::class.java)

        backBtn.setOnClickListener {
            finish()
        }

        userNameEdit.setText(viewModel.getUser().name)
        userEmailEdit.setText(viewModel.getUser().email)
        userIntroEdit.setText(viewModel.getUser().introduction)

        if (viewModel.getUser().logo != null) {
            Glide.with(this)
                .load(viewModel.getUser().logo)
                .into(userLogoImg)
        } else {
            Glide.with(this)
                .load(if (viewModel.getUser().sex == 1) R.drawable.ic_boy_logo else R.drawable.ic_girl_logo)
                .into(userLogoImg)
        }

        var sex = viewModel.getUser().sex

        userSexRadio.check(if (sex == 1) radioMan.id else radioWoman.id)

        userSexRadio.setOnCheckedChangeListener { _, checkedId ->
            sex = if (checkedId == radioMan.id) 1 else 2
        }

        submitBtn.setOnClickListener {
            viewModel.updateUserInfo(
                userNameEdit.text.toString(),
                userEmailEdit.text.toString(),
                sex!!,
                userIntroEdit.text.toString()
            )
        }

        userLogoImg.setOnClickListener {
            Matisse.from(this)
                .choose(MimeType.ofImage(), false) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(GlideEngine()) // 使用的图片加载引擎
                .forResult(12306) // 设置作为标记的请求码
        }


    }

    override fun subscribeOnView() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12306 && resultCode == RESULT_OK) {
            Glide.with(this)
                .load(Matisse.obtainResult(data))
                .into(userLogoImg)
        }
    }
}
