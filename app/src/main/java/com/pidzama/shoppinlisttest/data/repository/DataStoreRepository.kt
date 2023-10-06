package com.pidzama.shoppinlisttest.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.pidzama.shoppinlisttest.data.repository.DataStoreRepository.PreferencesKey.authSuccess
import com.pidzama.shoppinlisttest.utils.Constants.Companion.AUTHENTICATION_COMPLETED
import com.pidzama.shoppinlisttest.utils.Constants.Companion.AUTH_KEY
import com.pidzama.shoppinlisttest.utils.Constants.Companion.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

class DataStoreRepository(context: Context) {

    private val dataStore = context.dataStore

    private object PreferencesKey {
        val authSuccess = booleanPreferencesKey(name = AUTHENTICATION_COMPLETED)
    }

    suspend fun authenticationState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[authSuccess] = completed
        }
    }

    fun readOnAuthState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState =
                    preferences[booleanPreferencesKey(name = AUTHENTICATION_COMPLETED)] ?: false
                onBoardingState
            }
    }

    suspend fun saveAuthKey(key: String) {
        dataStore.edit { pref ->
            pref[stringPreferencesKey(AUTH_KEY)] = key

        }
    }

    fun getKey() = dataStore.data.map { pref ->
        pref[stringPreferencesKey(AUTH_KEY)] ?: "0"
    }
}
