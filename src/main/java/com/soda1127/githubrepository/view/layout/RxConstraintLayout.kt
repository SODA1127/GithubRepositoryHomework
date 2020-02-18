package com.soda1127.githubrepository.view.layout

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by leekijung on 2020. 1. 28..
 */

class RxConstraintLayout
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : ConstraintLayout(context, attrs, defStyleAttr), LayoutLifecycleProvider {
    private val lifecycleSubject =
        BehaviorSubject.create<LayoutEvent>()

    override fun <T> bindUntilEvent(event: LayoutEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleSubject.onNext(LayoutEvent.ON_ATTACHED_TO_WINDOW)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleSubject.onNext(LayoutEvent.ON_DETACHED_FROM_WINDOW)
    }
}