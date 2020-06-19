package com.cai.campus.features.message.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.UserSignInDetail
import com.cai.campus.common.utils.Change
import com.cai.campus.common.utils.Check


@SuppressLint("SetTextI18n")
class MySignInRecyclerAdapter constructor(data: List<UserSignInDetail>) :
    BaseRecyclerAdapter<UserSignInDetail>(data, R.layout.item_my_sign_in) {
    override fun bindData(holder: BaseViewHolder?, position: Int, detail: UserSignInDetail) {
        val detailTv = holder?.getView(R.id.detailTv) as TextView
        val statusTv = holder.getView(R.id.statusTv) as TextView
        val timeTv = holder.getView(R.id.timeTv) as TextView
        val groupNameTv = holder.getView(R.id.groupNameTv) as TextView
        val isDoneTv = holder.getView(R.id.isDoneTv) as TextView
        val isDoneFlag = holder.getView(R.id.isDoneFlag) as View

        detailTv.text =
            if (detail.signIn!!.detail.isNullOrEmpty()) "未命名签到" else detail.signIn!!.detail

        groupNameTv.text = detail.groupName

        timeTv.text =
            Change.timestampToTime(detail.signIn!!.createTime.toString()) + " > " + Change.secondsToMin(
                detail.signIn!!.endTime!! - detail.signIn!!.createTime!!
            ) + "分钟"

        val signInStatus = Check.getSignInStatus(detail.signIn!!)

        statusTv.text = when (signInStatus) {
            Check.SIGN_IN_STATUS_READY -> "未开始"
            Check.SIGN_IN_STATUS_END -> "已结束"
            Check.SIGN_IN_STATUS_GOING -> "进行中"
            else -> "ERROR"
        }
        statusTv.setTextColor(
            Color.parseColor(
                when (signInStatus) {
                    Check.SIGN_IN_STATUS_READY -> "#409eff"
                    Check.SIGN_IN_STATUS_END -> "#96979b"
                    Check.SIGN_IN_STATUS_GOING -> "#66c33a"
                    else -> "ERROR"
                }
            )
        )

        if (detail.record!!.isDone == 0) {
            isDoneTv.text = "未签到"
            isDoneTv.setTextColor(Color.parseColor("#f4433c"))
            isDoneFlag.visibility = View.VISIBLE
        } else {
            isDoneTv.text = "已签到"
            isDoneFlag.visibility = View.INVISIBLE
        }

    }
}