package com.soda1127.githubrepository.view.adapter.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Created by leekijung on 2020. 1. 28..
 */

class LinearLayoutManagerWrapper : LinearLayoutManager {
    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) :
            super(
                context,
                orientation,
                reverseLayout)


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(
                context,
                attrs,
                defStyleAttr,
                defStyleRes)

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}