/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.gateway

interface AuthenticationSource {

    suspend fun isAuthenticated(): Boolean

    suspend fun signOut()
}
