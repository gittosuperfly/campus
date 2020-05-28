package com.cai.campus.features

import android.Manifest
import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cai.campus.R
import com.cai.campus.common.router.RequestCode
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wildma.pictureselector.FileUtils
import com.wildma.pictureselector.PictureBean
import com.wildma.pictureselector.PictureSelector
import kotlinx.android.synthetic.main.activity_main.*


@Route(path = RouterPath.MAIN_PAGE)
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home.setOnClickListener {
            ARouter.getInstance().build(RouterPath.HOME_PAGE).navigation()
        }

        login.setOnClickListener {
            ARouter.getInstance().build(RouterPath.LOGIN_PAGE).navigation()
        }

        scanQRCode.setOnClickListener {
            RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe {
                    if (it) {
                        ARouter.getInstance().build(RouterPath.SCAN_QR_CODE)
                            .navigation(this, RequestCode.SCAN_QR_CODE)
                    } else {
                        Prompt.show("给我相机权限我才能扫码啊......")
                    }
                }
        }

        imageTest.setOnClickListener {
            PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
        }


    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // 扫描二维码/条码回传
        if (requestCode == RequestCode.SCAN_QR_CODE) {
            if (data != null) {
                //返回的文本内容
                val content = data.getStringExtra(DECODED_CONTENT_KEY)
                //返回的BitMap图像
                val bitmap =
                    data.getParcelableExtra<Bitmap>(DECODED_BITMAP_KEY)

                codeText.text = "你扫描到的内容是：$content"
            } else {
                Prompt.show("DATA = NULL")
            }
        }
    }

    companion object {
        const val DECODED_CONTENT_KEY = "codedContent"
        const val DECODED_BITMAP_KEY = "codedBitmap"
    }

}
