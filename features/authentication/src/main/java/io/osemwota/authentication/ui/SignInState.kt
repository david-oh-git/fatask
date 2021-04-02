/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.ui

sealed class SignInState {

    object Error : SignInState()

    object Progress : SignInState()

    object Success : SignInState()
}
