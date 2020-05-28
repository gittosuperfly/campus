package com.cai.campus.features.group

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.cai.campus.R
import com.cai.campus.common.router.RouterPath
import com.cai.campus.common.utils.Prompt
import com.cai.campus.features.group.adapter.GroupRecyclerAdapter
import kotlinx.android.synthetic.main.edit_dialog_layout.*
import kotlinx.android.synthetic.main.group_fragment.*


class GroupFragment : Fragment() {

    private lateinit var viewModel: GroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.group_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllGroup()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GroupViewModel::class.java)

        createGroupTv.setOnClickListener {
            val content: View =
                LayoutInflater.from(context).inflate(R.layout.edit_dialog_layout, null)
            val edit = content.findViewById<EditText>(R.id.dialogEdit)
            edit.hint = "请输入群名称"
            AlertDialog.Builder(activity).setTitle("创建群组").setView(content).setPositiveButton(
                "确定"
            ) { _, _ -> //按下确定键后的事件
                viewModel.createGroup(edit.text.toString())
            }.setNegativeButton("取消", null).show()
        }

        addGroupTv.setOnClickListener {
            val content: View =
                LayoutInflater.from(context).inflate(R.layout.edit_dialog_layout, null)
            val edit = content.findViewById<EditText>(R.id.dialogEdit)
            edit.hint = "请输入群ID"
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("加入群组").setView(content).setPositiveButton(
                "确定"
            ) { _, _ -> //按下确定键后的事件
                viewModel.addGroup(edit.text.toString().toInt())
            }.setNegativeButton("取消", null).show()
        }


        val groupAdapter = GroupRecyclerAdapter(listOf())
        groupRecyclerView.layoutManager = LinearLayoutManager(activity)
        groupRecyclerView.adapter = groupAdapter

        viewModel.groupList.observe(this.viewLifecycleOwner, Observer {

            if (it.isEmpty()) {
                noDataImage.visibility = View.VISIBLE
            }

            groupAdapter.setOnItemClickListner { _, position ->
                Prompt.show(it[position].name)
                ARouter.getInstance()
                    .build(RouterPath.GROUP_DETAIL)
                    .withSerializable("group", it[position]!!)
                    .navigation()
            }
            groupAdapter.refresh(it)
        })

    }

}
