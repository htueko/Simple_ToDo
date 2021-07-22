package com.htueko.simpletodo.common.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * extension function [Context] to get [DataStore]
 * @see <a href="https://developer.android.com/topic/libraries/architecture/datastore#kotlin">DataStore Android Doc</a>
 */
private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(
            name = PreferenceConstant.NAME_USER_PREFERENCES
        )

/**
 * to store user data in preferences
 * @see [AppPreferences]
 * @see [PreferenceConstant]
 */
class AppPreferencesImpl @Inject constructor(
    @ApplicationContext context: Context
) : AppPreferences{

    // get the DataStore object to store user data
    private val dataStore = context.dataStore

    // keys to read and write
    private object PreferencesKey {
        val KEY_LOGGED_IN = booleanPreferencesKey(PreferenceConstant.KEY_IS_LOGIN)
    }

    override fun isLoggedIn(): Flow<Boolean> =
        dataStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // get is logged in value, defaulting to false if not set:
            preferences[PreferencesKey.KEY_LOGGED_IN] ?: false
        }

    override suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit { preferences ->
            // set is logged in value
            preferences[PreferencesKey.KEY_LOGGED_IN] = value
        }
    }

}