/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication

import android.content.Context
import io.osemwota.authentication.gateway.AuthenticationSource
import io.osemwota.authentication.gateway.AuthenticationSourceImpl

object AuthenticationGraph {

    fun provideAuthenticationSource(context: Context): AuthenticationSource {
        return AuthenticationSourceImpl(
            context
        )
    }
}
