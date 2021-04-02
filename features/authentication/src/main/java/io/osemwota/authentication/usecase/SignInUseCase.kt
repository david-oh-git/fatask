/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.usecase

import io.osemwota.authentication.data.SignInResult
import io.osemwota.authentication.gateway.AuthenticationSource

class SignInUseCase(
    private val authenticationSource: AuthenticationSource
) {

    suspend fun execute(): SignInResult {
        return when (authenticationSource.isAuthenticated()) {
            true -> SignInResult.Success
            false -> SignInResult.Error
        }
    }
}
