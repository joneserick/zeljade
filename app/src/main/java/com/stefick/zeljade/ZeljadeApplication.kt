package com.stefick.zeljade

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZeljadeApplication : Application() {

    companion object {
        const val ZELJADE_APP_URI = "https://stefick.com/zeljade"
    }

}