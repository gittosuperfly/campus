package com.cai.campus.features.message.adapter

import android.widget.TextView
import com.cai.campus.R
import com.cai.campus.app.BaseRecyclerAdapter
import com.cai.campus.common.network.model.GroupMessage
import com.cai.campus.common.utils.Change

class MyMessageRecyclerAdapter constructor(data: List<GroupMessage>) :
    BaseRecyclerAdapter<GroupMessage>(data, R.layout.item_my_msg) {
    override fun bindData(holder: BaseViewHolder?, position: Int, message: GroupMessage) {
        val detailTv = holder?.getView(R.id.detailTv) as TextView
        val timeTv = holder.getView(R.id.timeTv) as TextView
        val groupNameTv = holder.getView(R.id.groupNameTv) as TextView

        detailTv.text = message.notice!!.notice
        groupNameTv.text = message.groupName
        timeTv.text = Change.timestampToTime(message.notice!!.releaseTime.toString())
    }
}