/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.fatask.ui.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class LauncherViewModel : ViewModel() {

    lateinit var checkIfUserIsAuthenticated: (() -> Boolean)
    val isUserAuthenticated = liveData {
//        emit(authenticationSource.isAuthenticated())
        emit(checkIfUserIsAuthenticated.invoke())
    }
}
