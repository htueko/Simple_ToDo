package com.htueko.simpletodo

import android.app.Application
import com.htueko.simpletodo.common.util.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleTodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }
}
