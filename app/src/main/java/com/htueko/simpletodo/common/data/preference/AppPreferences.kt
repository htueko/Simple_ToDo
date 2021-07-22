package com.htueko.simpletodo.common.data.preference

import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for DataStore Preferences
 * @see [AppPreferencesImpl]
 */
interface AppPreferences {
    fun isLoggedIn(): Flow<Boolean>
    suspend fun setLoggedIn(value: Boolean)
}