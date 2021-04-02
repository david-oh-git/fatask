/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.utility.extentions

import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

fun sendSnack(parent: ViewGroup, message: String) {
    Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
}

fun sendLongSnack(parent: ViewGroup, message: String) {
    Snackbar.make(parent, message, Snackbar.LENGTH_LONG).show()
}
