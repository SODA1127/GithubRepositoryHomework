package com.soda1127.githubrepository.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.model.Banner
import com.soda1127.githubrepository.model.Data
import com.soda1127.githubrepository.model.User
import com.soda1127.githubrepository.view.custom.SrcImageView

class UserViewHolder(itemView : View) : DataViewHolder(itemView) {

    private val TAG = "UserViewHolder"

    private val userImage by lazy<SrcImageView> { itemView.findViewById(R.id.user_image) }
    private val userName by lazy<TextView> { itemView.findViewById(R.id.user_name) }

    override fun reset() {
        userName.text = ""
        userImage.clear()
    }

    override fun bindViews(data: Data) {
        if (data is User) {
            userName.text = data.login
            userImage.render(data.avatarUrl)
            itemView.setOnClickListener { data.runDetail() }
        }
    }

}