package com.soda1127.githubrepository.view.layout

import androidx.annotation.CheckResult
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * Created by leekijung on 2020. 2. 18..
 */

interface LayoutLifecycleProvider {
    @CheckResult
    fun <T> bindUntilEvent(event: LayoutEvent): LifecycleTransformer<T>
}