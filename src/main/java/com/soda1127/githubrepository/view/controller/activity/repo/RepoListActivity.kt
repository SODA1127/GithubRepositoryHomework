package com.soda1127.githubrepository.view.controller.activity.repo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.helper.base.MinorHelper.toast
import com.soda1127.githubrepository.view.controller.activity.DataListActivity
import com.soda1127.githubrepository.viewmodel.issue.RepoListViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by leekijung on 2020. 2. 18..
 */

class RepoListActivity : DataListActivity() {

    private val backButtonSubject = BehaviorSubject.createDefault(0L)

    override fun getLayoutId() = R.layout.activity_repo_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        if (Intent.ACTION_VIEW == intent.action) {
            val uri: Uri? = intent.data
            if (uri != null) {
                val userName: String? = uri.getQueryParameter("user_name")
                userName?.let {
                    (viewModel as RepoListViewModel).userName.set(it)
                }
            }
        }

        initData()
    }

    override fun initViews() {
        super.initViews()
        observeBackButtonEvent()
    }

    override fun initViewModel() = RepoListViewModel()

    override fun onBackPressed() = backButtonSubject.onNext(System.currentTimeMillis())

    private fun observeBackButtonEvent() {
        backButtonSubject.toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(AndroidSchedulers.mainThread())
            .compose(bindToLifecycle())
            .buffer(2, 1)
            .filter {
                if (it[1] - it[0] > 1500) {
                    toast(R.string.terminate_app_with_click_twice)
                    return@filter false
                }
                return@filter true
            }
            .subscribe { finish() }.add()
    }
}
