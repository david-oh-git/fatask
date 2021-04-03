/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.utility

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UtilityGraph : DataStoreSource {

    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by
    preferencesDataStore(name = "settings")

    override suspend fun save(context: Context, key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { db ->
            db[prefKey] = value
        }
    }

    override fun read(context: Context, key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data.map { db ->
            db[prefKey] ?: "Hello Mr Robot"
        }
    }
}
