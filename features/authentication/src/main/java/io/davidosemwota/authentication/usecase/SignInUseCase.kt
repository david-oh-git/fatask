/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication.usecase

import io.davidosemwota.authentication.data.Result
import io.davidosemwota.authentication.data.SignInResult
import io.davidosemwota.authentication.gateway.AuthenticationSource
import timber.log.Timber

class SignInUseCase(
    private val authenticationSource: AuthenticationSource
) {

    suspend fun execute(idToken: String): SignInResult {
        return try {
            val result = authenticationSource.signIn(idToken)
            if (result.isSuccess())
                SignInResult.Success
            else {
                val error = result as Result.Error
                Timber.d("Error is ${error.throwable.message}")
                SignInResult.Error
            }
        } catch (throwable: Throwable) {
            Timber.d("Error is ${throwable.message}")
            SignInResult.Error
        }
    }
}
