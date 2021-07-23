package com.htueko.simpletodo.common.domain.executor

import kotlinx.coroutines.CoroutineDispatcher

interface AppExecutor {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
