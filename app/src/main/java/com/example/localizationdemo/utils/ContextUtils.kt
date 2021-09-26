package com.example.localizationdemo.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import java.util.*


class ContextUtils(base: Context?) : ContextWrapper(base) {
    companion object {
        fun updateLocale(context: Context, localeToSwitchTo: Locale?): ContextWrapper {
            var context = context
            val resources = context.resources
            val configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = localeToSwitchTo
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return ContextUtils(context)
        }

        fun saveLocale(context: Context, lang: String){
            var langPref = "Language";
            val prefs: SharedPreferences = context.getSharedPreferences("CommonPrefs", MODE_PRIVATE)
            var editor: SharedPreferences.Editor = prefs.edit()
            editor.putString(langPref, lang);
            editor.commit();
        }

        fun loadLocale(context: Context): String? {
            val langPref = "Language"
            val prefs: SharedPreferences = context.getSharedPreferences("CommonPrefs", MODE_PRIVATE)
            return prefs.getString(langPref, "")
        }
    }
}