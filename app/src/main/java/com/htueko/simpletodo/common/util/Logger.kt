package com.htueko.simpletodo.common.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber

object Logger {

    fun init() {
        setupTimber()
        setupLogging()
    }

    /**
     * to log using Timber library
     * @see [https://github.com/JakeWharton/timber]
     */
    private fun setupTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(
                priority: Int,
                tag: String?,
                message: String,
                t: Throwable?
            ) {
                Logger.log(priority, "-$tag", message, t)
            }
        })
    }

    /**
     * to setup logging
     * @see [https://github.com/orhanobut/logger]
     */
    private fun setupLogging() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // (Optional) Whether to show thread info or not. Default true
            .methodCount(1) // (Optional) How many method line to show. Default 2
            .methodOffset(5) // Set methodOffset to 5 in order to hide internal method calls
            .tag("") // To replace the default PRETTY_LOGGER tag with a dash (-).
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}
