package com.soda1127.githubrepository.view.controller.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soda1127.githubrepository.helper.data.DataBindHelper
import com.soda1127.githubrepository.view.adapter.DataRecyclerAdapter
import com.soda1127.githubrepository.view.adapter.layoutmanager.LinearLayoutManagerWrapper
import com.soda1127.githubrepository.viewmodel.DataListViewModel
import com.soda1127.githubrepository.R

/**
 * Created by leekijung on 2020. 2. 18..
 */

abstract class DataListActivity : BaseActivity() {

    protected lateinit var viewModel: DataListViewModel
    protected lateinit var dataRecyclerAdapter: DataRecyclerAdapter
    protected lateinit var recyclerView: RecyclerView
    protected var emptyResultText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        dataRecyclerAdapter = DataRecyclerAdapter(viewModel.dataList)
        initViews()
        bindViews()
    }

    open fun initViews() {
        recyclerView = findViewById(R.id.recycler_view)
        emptyResultText = findViewById(R.id.empty_result_text)
        recyclerView.adapter = dataRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManagerWrapper(this, RecyclerView.VERTICAL, false)
    }

    abstract fun initViewModel(): DataListViewModel

    open fun bindViews() {
        viewModel.dataListToAdd.subscribe(lifecycle, {
            recyclerView.visibility = if (it.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
            emptyResultText?.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            viewModel.clearHandler(it)
            dataRecyclerAdapter.dispatchDataList(it)
            DataBindHelper.getInstance().bindList(it, this, viewModel)
        }, { })?.add()
    }

    open fun initData() = viewModel.getDataList(null, null, null, null)

    override fun onDestroy() {
        viewModel.clearDisposable()
        super.onDestroy()
    }

}
