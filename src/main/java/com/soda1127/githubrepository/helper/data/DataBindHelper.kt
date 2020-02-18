package com.soda1127.githubrepository.helper.data

import android.content.Context
import com.soda1127.githubrepository.viewmodel.BaseViewModel
import com.soda1127.githubrepository.data.binder.RepositoryBinder
import com.soda1127.githubrepository.model.Data
import com.soda1127.githubrepository.model.Repo
import com.soda1127.githubrepository.basic.Type.*
import com.soda1127.githubrepository.data.binder.UserBinder
import com.soda1127.githubrepository.model.User

/**
 * Created by leekijung on 2020. 1. 28..
 */

class DataBindHelper {

    fun bindList(list: List<Data>, context: Context, viewModel: BaseViewModel) {
        list.forEach { bindData(it, context, viewModel) }
    }

    private fun bindData(data: Data, context: Context, viewModel: BaseViewModel) {
        when(data.type) {
            REPO_CELL.type -> RepositoryBinder.bindRepository(context, viewModel, data as Repo)
            USER_CELL.type -> UserBinder.bindUser(context, viewModel, data as User)
            else -> { }
        }
    }

    companion object {
        private var instance: DataBindHelper? = null

        fun getInstance(): DataBindHelper {
            if (instance == null) instance = DataBindHelper()
            return instance as DataBindHelper
        }
    }

}
