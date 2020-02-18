package com.soda1127.githubrepository.basic

import com.soda1127.githubrepository.model.Data

typealias DataHandler = ((T : Data) -> Unit)

typealias ActionHandler = () -> Unit