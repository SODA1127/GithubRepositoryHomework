package com.soda1127.githubrepository.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.model.Data
import com.soda1127.githubrepository.model.Repo

/**
 * Created by leekijung on 2020. 1. 28..
 */

class RepoViewHolder(itemView : View) : DataViewHolder(itemView) {

    private val TAG = "RepoViewHolder"

    private val repoName by lazy<TextView> { itemView.findViewById(R.id.repo_name) }
    private val repoDesc by lazy<TextView> { itemView.findViewById(R.id.repo_desc) }
    private val repoStarCount by lazy<TextView> { itemView.findViewById(R.id.repo_star_count) }

    override fun reset() {
        repoName.text = ""
        repoDesc.text = ""
        repoDesc.visibility = View.GONE
    }

    override fun bindData(data : Data) {
        super.bindData(data)
        if (data is Repo) {
            repoName.text = data.name
            data.description?.let {
                repoDesc.visibility = View.VISIBLE
                repoDesc.text = it
            }
            repoStarCount.text = data.stargazersCount.toString()
        }
    }

    override fun bindViews(data: Data) {
        if (data is Repo) {
            itemView.setOnClickListener { data.runDetail() }
        }
    }
}