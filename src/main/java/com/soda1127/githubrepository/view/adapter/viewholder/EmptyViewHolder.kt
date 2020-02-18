package com.soda1127.githubrepository.view.adapter.viewholder

import android.view.View
import com.soda1127.githubrepository.model.Data

/**
 * Created by leekijung on 2020. 2. 18..
 */

class EmptyViewHolder(itemView: View) : DataViewHolder(itemView) {

    override fun reset() {}

    override fun bindData(data: Data) { }

    override fun bindViews(data: Data) { }
}