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

//        if (position < 9) {
        setItemImageDrawable(
            userLogo,
            if (data.userInfo.sex == 1) DefaultLogo.boyLogoResId else DefaultLogo.girlLogoResId
        )

        if (data.userInfo.name.isNullOrEmpty() || data.userInfo.name == "") {
            userName.text = "未命名用户${data.userInfo.uid}"
        } else {
            userName.text = data.userInfo.name
        }

        when (data.status) {
            2 -> {
                val flag = holder?.getView(R.id.leaderFlag) as View
                flag.visibility = View.VISIBLE
            }
            1 -> {
                val flag = holder?.getView(R.id.adminFlag) as View
                flag.visibility = View.VISIBLE
            }
            0 -> {
            }
        }
//        }
//        else {
//            setItemImageDrawable(userLogo, R.drawable.ic_more)
//            userName.text = "更多..."
//        }
    }
//
//    override fun getItemCount(): Int {
//        return when {
//            dataList.isNullOrEmpty() -> 0
//            dataList.size >= 10 -> 10
//            else -> dataList.size
//        }
//    }
}