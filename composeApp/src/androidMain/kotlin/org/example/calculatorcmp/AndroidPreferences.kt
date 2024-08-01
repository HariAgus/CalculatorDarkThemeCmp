package org.example.calculatorcmp

import android.content.Context
import android.preference.PreferenceManager
import utils.Preferences

class AndroidPreferences(private val context: Context) : Preferences {

    private val DARK_THEME_KEY = "dark_theme_key"

    override fun setDarkThemeEnabled(isEnabled: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().putBoolean(DARK_THEME_KEY, isEnabled).apply()
    }

    override fun isDarkThemeEnabled(): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(DARK_THEME_KEY, false)
    }


}