package com.pidzama.shoppinlisttest.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pidzama.shoppinlisttest.utils.Constants.Companion.AUTH_KEY
import com.pidzama.shoppinlisttest.utils.Constants.Companion.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)

class DataStoreManager(val context: Context) {

    suspend fun saveAuthKey(key: String) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey(AUTH_KEY)] = key

        }
    }

    fun getKey() = context.dataStore.data.map { pref ->
        pref[stringPreferencesKey(AUTH_KEY)] ?: "0"
    }
}
