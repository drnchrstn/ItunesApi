package com.fourello.itunesapi

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

class App: Application() {


    override fun onCreate() {
        super.onCreate()
        initPrefs()
    }


    fun initPrefs(){
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}