/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.fatask

import android.app.Application
import io.osemwota.payment.PaymentGraph
import timber.log.Timber

/**
 * [Application] class for the app.
 */
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
