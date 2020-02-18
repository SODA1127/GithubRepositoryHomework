package com.soda1127.githubrepository.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.soda1127.githubrepository.view.adapter.viewholder.EmptyViewHolder
import com.soda1127.githubrepository.view.adapter.viewholder.RepoViewHolder
import com.soda1127.githubrepository.view.adapter.viewholder.DataViewHolder
import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.view.adapter.viewholder.UserViewHolder

/**
 * Created by leekijung on 2020. 2. 18..
 */

object DataViewHolderMapper {

    fun map(parent: ViewGroup, layoutId: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return when (layoutId) {
            R.layout.viewholder_repo -> RepoViewHolder(itemView)
            R.layout.viewholder_user -> UserViewHolder(itemView)
            else -> EmptyViewHolder(itemView)
        }
    }
}
