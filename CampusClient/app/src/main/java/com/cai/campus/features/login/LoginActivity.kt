package com.cai.campus.features.login

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.JNIUtils
import com.cai.campus.common.utils.Prompt
import com.cai.campus.common.utils.RSAUtils
import kotlinx.android.synthetic.main.login_activity.*
import java.nio.charset.Charset


@Route(path = RouterPath.LOGIN_PAGE)
class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel
    private var loginBtnState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.login_activity)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        subscribeViewEvent()
        setViewClickListener()
        setViewAnimation()

        var data = byteArrayOf()

        data = RSAUtils.encryptData(
            "this is json".toByteArray(Charset.forName("UTF-8")),
            RSAUtils.loadPublicKey(String(JNIUtils.getRsaPublicKey()))
        )

        Log.d("rsa_test", "加密后：" + data.contentToString())

        val result = RSAUtils.decryptData(
            data,
            RSAUtils.loadPrivateKey(
                "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAu4a7+nsg5cde9vD1" +
                        "hxRTX1haa7KIGpxm68AWEPsBU9lWn8pFF/ne0c4Cuvwnx1j93GzJ+FhtQHsWISUf" +
                        "gHtlPwIDAQABAkAC0A+Lv0MMD0R+YbjdENH7USDyEph2BbiJerUaBhnL/yT5ecpd" +
                        "2q7myD0b+OSA2hanxLajL3yvRsLd4+YRnCbZAiEA355N6SpRniTPV/5oDjaY30IX" +
                        "iGvXk7zTzwh2X79BQTUCIQDWrnh+ZbODvqrWbviAPYUhgTxo5lF8V8kYkhpQ8NPv" +
                        "IwIgecAVJ1gVLAdapMwtF1/+h7/SVEJzE0sB/3zaBnN5MekCID72eqZvg3/PKuGr" +
                        "ODy8TlBuS95kNl/mizcmmotEppPLAiAXmdg3zeFvZMwwxJQOYERh4G0eTNuGdB4A" +
                        "HdeoXarskw=="
            )
        )

        var str = ""
        for (element in result) {
            str += element.toChar()
        }
        Log.d("rsa_test", "解密后：" + str)


    }

    override fun subscribeOnView() {
        viewModel.apiData.observe(this, Observer {
            Prompt.show(it.message)
            if (it.result == 1) {
                ARouter.getInstance().build(RouterPath.HOME_PAGE).navigation()
                finish()
            }
        })
    }


    /* 设置页面点击事件 */
    private fun setViewClickListener() {
        submitBtn.setOnClickListener {
            if (loginBtnState) {
                viewModel.login(phoneEdit.text.toString(), passwordEdit.text.toString())
            } else {
                Prompt.show("请检查账号密码")
            }
        }

        toRegisterBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouterPath.REGISTER_PAGE)
                .withInt("type", 1)
                .withString("pageName", "新用户注册")
                .navigation()
        }

        toFindPasswordBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouterPath.REGISTER_PAGE)
                .withInt("type", 2)
                .withString("pageName", "找回密码")
                .navigation()
        }
    }

    /* 设置页面动画 */
    private fun setViewAnimation() {
        ObjectAnimator.ofFloat(loginFormLayout, "translationY", 250f, 0f)
            .setDuration(1000).start()
    }

    /* 设置页面监听 */
    private fun subscribeViewEvent() {
        phoneEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                phoneEdit.hint = ""
            } else {
                phoneEdit.hint = getString(R.string.phoneHint)
            }
        }

        passwordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEdit.hint = ""
            } else {
                passwordEdit.hint = getString(R.string.passwordHint)
            }
        }

        passwordEdit.setTextChangeListener {
            changeLoginBtnBackground()
        }
        phoneEdit.setTextChangeListener {
            changeLoginBtnBackground()
        }
    }


    /* 设置登录按钮状态 */
    private fun changeLoginBtnBackground() {
        if (phoneEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty()) {
            if (!loginBtnState) {
                submitBtn.setBackgroundResource(R.drawable.shape_login_btn_on)
                loginBtnState = true
            }
        } else {
            if (loginBtnState) {
                submitBtn.setBackgroundResource(R.drawable.shape_login_btn_off)
                loginBtnState = false
            }
        }
    }
}