package com.cai.campus.features.message.signIn

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.CameraUpdate
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.amap.api.maps2d.model.MyLocationStyle
import com.cai.campus.R
import com.cai.campus.app.BaseActivity
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.network.model.UserSignInDetail
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Change
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_sign_in.*


@Route(path = RouterPath.USER_SIGN_IN)
@SuppressLint("SetTextI18n")
class UserSignInActivity : BaseActivity() {

    private lateinit var viewModel: UserSignInViewModel
    private lateinit var signInDetail: UserSignInDetail
    private lateinit var aMap: AMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity(this, R.layout.activity_user_sign_in)
        signInMap.onCreate(savedInstanceState)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(UserSignInViewModel::class.java)
        signInDetail = intent.getSerializableExtra(ExtraKey.USER_SIGN_IN_DETAIL) as UserSignInDetail

        detailTv.text = signInDetail.signIn!!.detail
        groupNameTv.text = signInDetail.groupName
        startTimeTv.text = Change.timestampToTime(signInDetail.signIn!!.createTime.toString())
        durationTv.text =
            "持续时间 ${(signInDetail.signIn!!.endTime!! - signInDetail.signIn!!.createTime!!) / 60} 分钟"

        aMap = signInMap.map
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16f))


        val adminLocation =
            Gson().fromJson(signInDetail.signIn!!.location!!, LocationBean::class.java)
        val latLng = LatLng(adminLocation.latitude, adminLocation.longitude)


        aMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("发布人")
                .snippet("DefaultMarker")
        )
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.interval(2000)
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)
        aMap.setMyLocationStyle(myLocationStyle)
        aMap.isMyLocationEnabled = true


        backBtn.setOnClickListener {
            finish()
        }

        signInBtn.setOnClickListener {
            viewModel.userSignIn(signInDetail.record!!)
        }
    }


    override fun subscribeOnView() {
        viewModel.isFinish.observe(this, Observer {
            if (it) {
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        signInMap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        signInMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        signInMap.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        signInMap.onSaveInstanceState(outState)
    }
}