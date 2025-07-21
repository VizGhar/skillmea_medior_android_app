package sk.skillmea.auth.data

import android.content.SharedPreferences
import androidx.core.content.edit

lateinit var preferences: SharedPreferences

fun storeAuthToken(token: String) {
    preferences.edit {
        putString("token", token)
    }
}

fun getAuthToken() = preferences.getString("token", null)