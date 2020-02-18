package com.soda1127.githubrepository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by leekijung on 2020. 2. 18..
 */

@Parcelize
data class Repo(
    var name: String,
    var description: String?,
    var owner: User,
    var stargazersCount: Int
): Data(), Parcelable
