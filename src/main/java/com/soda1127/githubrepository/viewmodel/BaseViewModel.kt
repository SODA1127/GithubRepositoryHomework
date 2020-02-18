package com.soda1127.githubrepository.viewmodel

import androidx.lifecycle.ViewModel
import com.soda1127.githubrepository.basic.ActionHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by leekijung on 2020. 1. 28..
 */

open class BaseViewModel : ViewModel() {

    protected var compositeSubscription = CompositeDisposable()

    protected fun run(runnable: ActionHandler?) {
        runnable?.invoke()
    }

    fun Disposable?.add(): Boolean {
        this?.let {
            compositeSubscription.add(it)
            return true
        }
        return false
    }
}