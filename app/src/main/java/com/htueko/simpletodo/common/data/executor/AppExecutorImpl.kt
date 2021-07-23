package com.htueko.simpletodo.common.data.executor

import com.htueko.simpletodo.common.domain.executor.AppExecutor
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppExecutorImpl @Inject constructor() : AppExecutor {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}