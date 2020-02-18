package com.soda1127.githubrepository.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner(override var type: String, override var url: String, val image: String): Data()