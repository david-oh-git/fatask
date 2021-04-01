/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.fatask.ui.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.davidosemwota.authentication.AuthenticationGraph
import io.davidosemwota.authentication.gateway.AuthenticationSource

class LauncherViewModel(
    private val authenticationSource: AuthenticationSource =
        AuthenticationGraph.provideAuthenticationSource()
) : ViewModel() {

    val isUserAuthenticated = liveData {
        emit(authenticationSource.isAuthenticated())
    }
}
