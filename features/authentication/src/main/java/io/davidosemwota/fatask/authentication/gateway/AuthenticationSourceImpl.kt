package io.davidosemwota.fatask.authentication.gateway

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.davidosemwota.fatask.authentication.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class AuthenticationSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthenticationSource {

    private val unknownError: Throwable
        get() = RuntimeException("An unknown error occurred during signing in in Firebase")

    override suspend fun isAuthenticated(): Boolean = firebaseAuth.currentUser != null

    override suspend fun signIn(idToken: String): Result<Boolean> {
        return firebaseAuth
            .signInWithCredential(GoogleAuthProvider.getCredential(idToken,null))
            .runCatching { await() }
            .map(AuthResult::getUser)
            .fold(
                { user ->
                    if (user != null) {
                        Result.Success(true)
                    }else {
                        Result.Error(unknownError)
                    }
                },
                { exception -> Result.Error(exception) }
            )
    }

    override suspend fun logOut() = withContext(ioDispatcher) {
        firebaseAuth.signOut()
    }

}