/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.gateway

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

internal class AuthenticationSourceImpl(
    private val context: Context
) : AuthenticationSource {

    override suspend fun isAuthenticated(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account != null
    }

    override suspend fun signOut() {
        GoogleSignIn.getClient(
            context,
            provideGoogleSignInOptions()
        ).signOut()
    }

    private fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }
}
