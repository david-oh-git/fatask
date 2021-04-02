/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.utility.extentions

import java.math.RoundingMode
import java.text.DecimalFormat

val Double.currencyFormat: String
    get() = DecimalFormat("#,###.##")
        .apply { roundingMode = RoundingMode.CEILING }.format(this)
