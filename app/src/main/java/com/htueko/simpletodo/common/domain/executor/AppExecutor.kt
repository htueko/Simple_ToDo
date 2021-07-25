package com.htueko.simpletodo.common.domain.executor

import com.htueko.simpletodo.common.data.executor.AppExecutorImpl
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Contract to implement for thread
 * @see [AppExecutorImpl]
 */
interface AppExecutor {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
