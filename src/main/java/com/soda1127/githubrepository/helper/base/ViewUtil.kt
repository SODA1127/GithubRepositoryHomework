package com.soda1127.githubrepository.helper.base

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.soda1127.githubrepository.GithubRepositoryApplication

/**
 * Created by leekijung on 2020. 2. 18..
 */

object ViewUtil {
    fun dpToPixel(dp: Int): Int {
        val resources = GithubRepositoryApplication.appContext?.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources?.displayMetrics).toInt()
    }

    fun clearLightStatusBar(view: View) {
        var flags = view.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        view.systemUiVisibility = flags
    }

    fun hideSoftInput(activity: Activity, input: EditText) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }

    fun showSoftInput(activity: Activity, input: EditText) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input, 0)
    }
}