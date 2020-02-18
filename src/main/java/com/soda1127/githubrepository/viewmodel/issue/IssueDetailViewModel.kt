package com.soda1127.githubrepository.viewmodel.issue

import com.soda1127.githubrepository.basic.ActionHandler
import com.soda1127.githubrepository.viewmodel.DataListViewModel

class IssueDetailViewModel: DataListViewModel() {
    override fun getDataList(
        startHandler: ActionHandler?,
        successHandler: ActionHandler?,
        failureHandler: ActionHandler?,
        endHandler: ActionHandler?
    ) {
        super.getDataList(startHandler, successHandler, failureHandler, endHandler)
    }
}