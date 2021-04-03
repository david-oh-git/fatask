/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.utility

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface DataStoreSource {

    suspend fun save(context: Context, key: String, value: String)

    fun read(context: Context, key: String): Flow<String>
}
