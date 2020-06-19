package com.cai.campus.features.group.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.model.LatLng
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.LocationBean
import com.cai.campus.common.network.model.UserSignInDetail
import com.google.gson.Gson

@SuppressLint("SetTextI18n")
class UserSignInSummaryAdapter constructor(data: List<UserSignInDetail>) :
    BaseRecyclerAdapter<UserSignInDetail>(data, R.layout.item_user_sign_in) {


    override fun bindData(holder: BaseViewHolder?, position: Int, detail: UserSignInDetail) {

        val userNameTv = holder?.getView(R.id.detailTv) as TextView
        val statusTv = holder.getView(R.id.statusTv) as TextView
        val warningTv = holder.getView(R.id.warningTv) as TextView
        val distanceTv = holder.getView(R.id.distanceTv) as TextView

        val adminLocation = Gson().fromJson(detail.signIn!!.location,LocationBean::class.java)

        userNameTv.text = detail.signIn!!.detail
        statusTv.text = if (detail.record!!.isDone == 0) "未签到" else "已签到"
        statusTv.setTextColor(
            Color.parseColor(
                if (detail.record!!.isDone == 0) "#f56b6c"
                else "#66c33a"
            )
        )

        if (detail.record!!.isChangeUUID == 1) {
            warningTv.visibility = View.VISIBLE
        }

        if (detail.record!!.isDone == 1) {
            val userLocation = Gson().fromJson(detail.record!!.location, LocationBean::class.java)
            val adminLatLng = LatLng(adminLocation.latitude, adminLocation.longitude)
            val userLatLng = LatLng(userLocation.latitude, userLocation.longitude)
            val distance = AMapUtils.calculateLineDistance(adminLatLng, userLatLng)
            distanceTv.text = "${distance.toInt()}米"
            distanceTv.visibility = View.VISIBLE
            if (distance.toInt() > 100){
                distanceTv.setTextColor(Color.parseColor("#FFAF45"))
            }
        }
    }
}