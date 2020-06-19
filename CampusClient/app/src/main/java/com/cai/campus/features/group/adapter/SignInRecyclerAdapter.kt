package com.cai.campus.features.group.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.SignIn
import com.cai.campus.common.utils.Change
import java.util.*

@SuppressLint("SetTextI18n")
class SignInRecyclerAdapter constructor(data: List<SignIn>) :
    BaseRecyclerAdapter<SignIn>(data, R.layout.item_sign_in_list) {
    override fun bindData(holder: BaseViewHolder?, position: Int, signIn: SignIn) {
        val detail = holder?.getView(R.id.detailTv) as TextView
        val status = holder.getView(R.id.statusTv) as TextView
        val time = holder.getView(R.id.timeTv) as TextView

        detail.text = signIn.signId.toString() + "" + signIn.detail
        time.text =
            Change.timestampToTime(signIn.createTime.toString()) + " > " + Change.secondsToMin(
                signIn.endTime!! - signIn.createTime!!
            ) + "分钟"

        val timestamp = System.currentTimeMillis() / 1000

        status.text = when {
            timestamp < signIn.createTime!! -> "未开始"
            timestamp > signIn.endTime!! -> "已结束"
            else -> "进行中"
        }
        status.setTextColor(
            Color.parseColor(
                when {
                    timestamp < signIn.createTime!! -> "#409eff"
                    timestamp > signIn.endTime!! -> "#96979b"
                    else -> "#66c33a"
                }
            )
        )

    }
}