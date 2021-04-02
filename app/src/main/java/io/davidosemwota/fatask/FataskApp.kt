/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.fatask

import android.app.Application
import io.davidosemwota.payment.PaymentGraph
import timber.log.Timber

class FataskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        PaymentGraph.initialisePaystackSdk(applicationContext)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
