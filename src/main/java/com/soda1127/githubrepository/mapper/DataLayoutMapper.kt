package com.soda1127.githubrepository.mapper

import com.soda1127.githubrepository.R
import com.soda1127.githubrepository.basic.Type
import com.soda1127.githubrepository.model.Data

/**
 * Created by leekijung on 2020. 1. 28..
 */

object DataLayoutMapper {

    fun map(data: Data): Int {
        return when (data.type) {
            Type.REPO_CELL.type -> R.layout.viewholder_repo
            Type.USER_CELL.type -> R.layout.viewholder_user
            else -> R.layout.viewholder_empty
        }
    }
}
