package com.cai.campus.features.message

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter

import com.cai.campus.R
import com.cai.campus.common.router.ExtraKey
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Check
import com.cai.campus.features.message.adapter.MyMessageRecyclerAdapter
import com.cai.campus.features.message.adapter.MySignInRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_message.*

@SuppressLint("SetTextI18n")
class MessageFragment : Fragment() {

    private lateinit var viewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserSignInList()
        viewModel.getUserMessageList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        // TODO: Use the ViewModel

        val signInAdapter = MySignInRecyclerAdapter(listOf())
        mySignInListRv.layoutManager = LinearLayoutManager(activity)
        mySignInListRv.adapter = signInAdapter

        val messageAdapter = MyMessageRecyclerAdapter(listOf())
        myMessageListRv.layoutManager = LinearLayoutManager(activity)
        myMessageListRv.adapter = messageAdapter

        viewModel.signInListData.observe(this.viewLifecycleOwner, Observer {
            val dataList = it.filter { detail ->
                detail.record!!.isDone == 0 &&
                        (Check.getSignInStatus(detail.signIn!!) == Check.SIGN_IN_STATUS_GOING
                                || Check.getSignInStatus(detail.signIn!!) == Check.SIGN_IN_STATUS_READY)
            }
            signInAdapter.setOnItemClickListner { _, position ->
                ARouter.getInstance()
                    .build(RouterPath.USER_SIGN_IN)
                    .withSerializable(ExtraKey.USER_SIGN_IN_DETAIL, dataList[position])
                    .navigation()
            }
            signInAdapter.refresh(dataList)
            sigInNumberTv.text = "群签到 ( ${dataList.size} )"

        })

        viewModel.messageListData.observe(this.viewLifecycleOwner, Observer {
            messageAdapter.setOnItemClickListner { _, position ->
                ARouter.getInstance()
                    .build(RouterPath.NOTICE_DETAIL)
                    .withSerializable(ExtraKey.GROUP_MESSAGE, it[position])
                    .navigation()
            }
            messageAdapter.refresh(it)
            messageNumberTv.text = "群消息 ( ${it.size} )"
        })
    }

}
