/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.fatask.authentication.gateway

import io.davidosemwota.fatask.authentication.data.Result

interface AuthenticationSource {

    suspend fun isAuthenticated(): Boolean

    suspend fun signIn(idToken: String): Result<Boolean>

    suspend fun logOut()
}
