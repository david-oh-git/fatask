/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication.usecase

import io.davidosemwota.authentication.data.SignInResult
import io.davidosemwota.authentication.gateway.AuthenticationSource

class SignInUseCase(
    private val authenticationSource: AuthenticationSource
) {

    suspend fun execute(idToken: String): SignInResult {
        return try {
            val result = authenticationSource.signIn(idToken)
            if (result.isSuccess())
                SignInResult.Success
            else
                SignInResult.Error
        } catch (throwable: Throwable) {
            SignInResult.Error
        }
    }
}
