package com.soda1127.githubrepository.data.binder

import android.content.Context
import com.soda1127.githubrepository.model.User
import com.soda1127.githubrepository.view.controller.activity.repo.RepoListActivity
import com.soda1127.githubrepository.viewmodel.BaseViewModel
import com.soda1127.githubrepository.viewmodel.issue.RepoListViewModel

object UserBinder {
    fun bindUser(context: Context, viewModel: BaseViewModel, user: User) {
        if (context is RepoListActivity && viewModel is RepoListViewModel) {
        }
    }
}