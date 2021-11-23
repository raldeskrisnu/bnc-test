package com.raldes.bnc.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val LOGIN_ACTIVITY = "login_activity"

    private val preferences: SharedPreferences = context.getSharedPreferences(LOGIN_ACTIVITY,Context.MODE_PRIVATE)

    var loginToken: String?
        get() = preferences.getString(LOGIN_ACTIVITY, "default")
        set(value) = preferences.edit().putString(LOGIN_ACTIVITY, value).apply()


}