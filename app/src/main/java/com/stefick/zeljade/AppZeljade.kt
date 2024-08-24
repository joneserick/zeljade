package com.stefick.zeljade

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppZeljade : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    /*

    Differences between Koin and Hilt
    Koin is runtime
    Dagger is at compile time (faster)
     */

}