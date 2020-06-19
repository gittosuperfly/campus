package com.cai.campus.features.group.adapter

import android.widget.ImageView
import android.widget.TextView
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.GroupAccount
import com.cai.campus.common.repository.globalValue.DefaultLogo
import java.util.*

class GroupRecyclerAdapter constructor(data: List<GroupAccount>) :
    BaseRecyclerAdapter<GroupAccount>(data, R.layout.item_group_list) {
    override fun bindData(holder: BaseViewHolder?, position: Int, groupAccount: GroupAccount) {
        val groupName = holder?.getView(R.id.groupListItemName) as TextView
        val groupLogo = holder.getView(R.id.groupLogoImg) as ImageView
        groupName.text = groupAccount.name
        setItemImage(groupLogo, DefaultLogo.squareUrl)
    }


}