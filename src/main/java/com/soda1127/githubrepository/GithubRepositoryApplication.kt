package com.soda1127.githubrepository

import android.app.Application
import android.content.Context

/**
 * Created by leekijung on 2020. 2. 18..
 */

class GithubRepositoryApplication : Application() {

    private fun init() {
        appContext = null
    }

    override fun onCreate() {
        super.onCreate()
        init()
        appContext = applicationContext
    }

    override fun onTerminate() {
        super.onTerminate()
        init()
    }

    companion object {
        val TAG = "App"
        var appContext: Context? = null
            private set
    }

}
