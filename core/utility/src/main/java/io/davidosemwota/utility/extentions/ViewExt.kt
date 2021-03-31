/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.utility.extentions

import android.view.View

fun View.makeGone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.makeVisible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.makeVisibleOrGone(visible: Boolean) {
    if (visible) {
        makeVisible()
    } else {
        makeGone()
    }
}
