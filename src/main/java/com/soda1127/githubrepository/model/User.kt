package com.soda1127.githubrepository.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var login: String,
    var number: Int,
    var avatarUrl: String): Data()