/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.osemwota.utility.SAVE_NAME_KEY
import timber.log.Timber

/**
 * Created by Sergey Chuprin on 03.04.2020.
 */
class GoogleSignInResultObserver(
    private val fragment: Fragment,
    private val onError: (ApiException) -> Unit,
    private val onSuccess: (context: Context, key: String, value: String) -> Unit
) : DefaultLifecycleObserver {

    private class GoogleSignInActivityResultContract(
        private val activity: Activity
    ) : ActivityResultContract<Unit, Task<GoogleSignInAccount>>() {

        override fun createIntent(context: Context, input: Unit?): Intent {
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            return GoogleSignIn.getClient(activity, gso).signInIntent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount> {
            return GoogleSignIn.getSignedInAccountFromIntent(intent)
        }
    }

    private val activity: FragmentActivity
        get() = fragment.requireActivity()

    private lateinit var resultLauncher: ActivityResultLauncher<Unit>

    override fun onCreate(owner: LifecycleOwner) {
        resultLauncher = activity.activityResultRegistry.register(
            "google_sign_in_result",
            owner,
            GoogleSignInActivityResultContract(activity),
            ActivityResultCallback(::handGoogleSignInResultWithFirebase)
        )
    }

    fun signIn() = resultLauncher.launch(Unit)

    private fun handGoogleSignInResultWithFirebase(task: Task<GoogleSignInAccount>) {
        val exception = task.exception
        if (exception != null) {
            val apiException = exception as ApiException
            Timber.d("An error occurred when signing in: ${apiException.message}")
            onError(apiException)
            return
        }
        task.result?.displayName?.let { onSuccess(activity, SAVE_NAME_KEY, it) }
    }
}
