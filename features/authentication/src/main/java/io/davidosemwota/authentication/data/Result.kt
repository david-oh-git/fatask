/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication.data

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
        }
    }

    fun isSuccess(): Boolean = this is Success
}
