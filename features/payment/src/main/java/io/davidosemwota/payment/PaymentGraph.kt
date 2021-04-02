/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.payment

import android.content.Context
import co.paystack.android.PaystackSdk

object PaymentGraph {

    fun initialisePaystackSdk(context: Context) {
        PaystackSdk.initialize(context.applicationContext)
    }
}
