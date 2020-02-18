package com.soda1127.githubrepository.helper.base

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soda1127.githubrepository.BuildConfig
import com.soda1127.githubrepository.GithubRepositoryApplication

/**
 * Created by leekijung on 2020. 1. 28..
 */

object MinorHelper {

    private var gson: Gson? = null
    fun getGson(): Gson? {
        if (gson == null) {
            gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }
        return gson
    }

    fun toast(textResId: Int = 0, text: String? = null, duration: Int = Toast.LENGTH_SHORT) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                GithubRepositoryApplication.appContext,
                text?: GithubRepositoryApplication.appContext?.resources?.getText(textResId),
                duration).show()
        }
    }

    fun logDebug(msg: String) {
        logDebug("", msg)
    }

    fun logDebug(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG && tag != null && msg != null) {
            Log.d(tag, msg)
        }
    }

    fun logException(ex: Exception) {
        logException("", ex)
    }

    fun logException(tag: String, ex: Exception) {
        logDebug(tag, ex.toString())
    }

    fun logException(tag: String, ex: Throwable) {
        logException(tag, Exception(ex))
    }
}