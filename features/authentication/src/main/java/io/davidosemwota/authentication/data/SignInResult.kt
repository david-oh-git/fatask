/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication.data

sealed class SignInResult {

    object Success : SignInResult()

    object Error : SignInResult()
}
