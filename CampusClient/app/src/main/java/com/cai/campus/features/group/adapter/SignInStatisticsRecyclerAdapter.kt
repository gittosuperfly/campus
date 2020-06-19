package com.cai.campus.features.group.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.model.LatLng
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.GroupSignInRecord
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.network.model.SignIn
import com.google.gson.Gson

@SuppressLint("SetTextI18n")
class SignInStatisticsRecyclerAdapter constructor(data: List<GroupSignInRecord>) :
    BaseRecyclerAdapter<GroupSignInRecord>(data, R.layout.item_sign_record) {

    lateinit var adminLocation: LocationBean


    override fun bindData(holder: BaseViewHolder?, position: Int, record: GroupSignInRecord) {

        val userNameTv = holder?.getView(R.id.userNameTv) as TextView
        val statusTv = holder.getView(R.id.statusTv) as TextView
        val warningTv = holder.getView(R.id.warningTv) as TextView
        val distanceTv = holder.getView(R.id.distanceTv) as TextView


        userNameTv.text = record.userName
        statusTv.text = if (record.record.isDone == 0) "未签到" else "已签到"
        statusTv.setTextColor(
            Color.parseColor(
                if (record.record.isDone == 0) "#f56b6c"
                else "#66c33a"
            )
        )

        if (record.record.isChangeUUID == 1) {
            warningTv.visibility = View.VISIBLE
        }

        if (record.record.isDone == 1) {
            val userLocation = Gson().fromJson(record.record.location, LocationBean::class.java)
            val adminLatLng = LatLng(adminLocation.latitude, adminLocation.longitude)
            val userLatLng = LatLng(userLocation.latitude, userLocation.longitude)
            val distance = AMapUtils.calculateLineDistance(adminLatLng, userLatLng)
            distanceTv.text = "${distance.toInt()}米"
            distanceTv.visibility = View.VISIBLE
            if (distance.toInt() > 100){
                distanceTv.setTextColor(Color.parseColor("#e6a13c"))
            }
        }
    }
}