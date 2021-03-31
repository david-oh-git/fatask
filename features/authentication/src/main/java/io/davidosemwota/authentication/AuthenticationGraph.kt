/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication

import com.google.firebase.auth.FirebaseAuth
import io.davidosemwota.authentication.gateway.AuthenticationSource
import io.davidosemwota.authentication.gateway.AuthenticationSourceImpl
import io.davidosemwota.authentication.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers

object AuthenticationGraph {

    val signInUseCase: SignInUseCase
        get() = SignInUseCase(provideAuthenticationSource())

    private fun provideAuthenticationSource(): AuthenticationSource {
        return AuthenticationSourceImpl(
            FirebaseAuth.getInstance(),
            Dispatchers.IO
        )
    }
}
