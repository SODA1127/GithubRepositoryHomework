package com.soda1127.githubrepository.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soda1127.githubrepository.view.adapter.viewholder.DataViewHolder
import com.soda1127.githubrepository.mapper.DataLayoutMapper
import com.soda1127.githubrepository.mapper.DataViewHolderMapper
import com.soda1127.githubrepository.model.Data

/**
 * Created by leekijung on 2020. 1. 28..
 */

class DataRecyclerAdapter(var dataList: List<Data>) : RecyclerView.Adapter<DataViewHolder>() {

    fun dispatchDataList(dataList: List<Data>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return DataLayoutMapper.map(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolderMapper.map(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindData(dataList[position])
        holder.bindViews(dataList[position])
    }
}
