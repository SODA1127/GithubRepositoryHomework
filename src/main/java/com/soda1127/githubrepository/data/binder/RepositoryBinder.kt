package com.soda1127.githubrepository.data.binder

import android.content.Context
import com.soda1127.githubrepository.helper.base.RedirectHelper
import com.soda1127.githubrepository.model.Repo
import com.soda1127.githubrepository.view.controller.activity.repo.RepoListActivity
import com.soda1127.githubrepository.viewmodel.BaseViewModel
import com.soda1127.githubrepository.viewmodel.issue.RepoListViewModel

object RepositoryBinder {
    fun bindRepository(context: Context, viewModel: BaseViewModel, repo: Repo) {
        if (context is RepoListActivity && viewModel is RepoListViewModel) {
        }
    }
}