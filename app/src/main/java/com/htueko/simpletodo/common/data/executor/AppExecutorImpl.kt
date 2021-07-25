package com.htueko.simpletodo.common.data.executor

import com.htueko.simpletodo.common.domain.executor.AppExecutor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * to choose where to execute
 * @see [AppExecutor]
 * @see [Dispatchers.Main]
 * @see [Dispatchers.IO]
 * @see [Dispatchers.Default]
 */
class AppExecutorImpl @Inject constructor() : AppExecutor {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}
