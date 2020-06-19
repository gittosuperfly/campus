package com.cai.campus.features.group.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.GroupUser
import com.cai.campus.common.repository.globalValue.DefaultLogo

@SuppressLint("SetTextI18n")
class UserGridRecyclerAdapter constructor(data: List<GroupUser>) :
    BaseRecyclerAdapter<GroupUser>(data, R.layout.group_user_list_item) {

    override fun bindData(holder: BaseViewHolder?, position: Int, data: GroupUser) {
        val userName = holder?.getView(R.id.userName) as TextView
        val userLogo = holder.getView(R.id.userLogo) as ImageView

        val flag2 = holder.getView(R.id.leaderFlag) as View
        val flag1 = holder.getView(R.id.adminFlag) as View

        setItemImageDrawable(
            userLogo,
            if (data.userInfo.sex == 1) DefaultLogo.boyLogoResId else DefaultLogo.girlLogoResId
        )

        userName.text =
            if (data.userInfo.name.isNullOrEmpty() || data.userInfo.name == "")
                "未命名用户${data.userInfo.uid}"
            else
                data.userInfo.name

        when (data.status) {
            2 -> {
                flag2.visibility = View.VISIBLE
                flag1.visibility = View.GONE
            }
            1 -> {
                flag1.visibility = View.VISIBLE
                flag2.visibility = View.GONE
            }
            0 -> {
                flag1.visibility = View.GONE
                flag2.visibility = View.GONE
            }
        }
    }
}