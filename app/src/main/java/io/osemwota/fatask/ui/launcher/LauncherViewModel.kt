/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.fatask.ui.launcher

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.osemwota.authentication.gateway.AuthenticationSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LauncherViewModel(
    application: Application,
    private val authenticationSource: AuthenticationSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    lateinit var checkIfUserIsAuthenticated: (() -> Boolean)
    val isUserAuthenticated = liveData {
        emit(authenticationSource.isAuthenticated())
    }

    fun signOut() = viewModelScope.launch(ioDispatcher) {
        authenticationSource.signOut()
    }
}
