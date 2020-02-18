package com.soda1127.githubrepository.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soda1127.githubrepository.model.Data

/**
 * Created by leekijung on 2020. 1. 28..
 */

abstract class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun reset()

    open fun bindData(data: Data) = reset()

    abstract fun bindViews(data: Data)
}

