package com.cai.campus.features.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cai.campus.R
import com.cai.campus.common.utils.Prompt
import kotlinx.android.synthetic.main.group_fragment.*


class GroupFragment : Fragment() {

    private lateinit var viewModel: GroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.group_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        // TODO: Use the ViewModel


        refreshLayout.setPullToRefreshListener {
            Prompt.show("666666")
        }

        createGroupTv.setOnClickListener {
            refreshLayout.isRefreshing = false
        }
    }

}
