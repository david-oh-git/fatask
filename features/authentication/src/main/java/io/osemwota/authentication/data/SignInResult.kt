/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.data

sealed class SignInResult {

    object Success : SignInResult()

    object Error : SignInResult()
}
