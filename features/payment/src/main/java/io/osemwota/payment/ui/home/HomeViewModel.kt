/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.payment.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val generateRandomBalance
        get() = listOf(0.0, 27.67, 500.00, 7450.20).random()
    private val _accountBalance = MutableLiveData(generateRandomBalance)
    val accountBalance = _accountBalance

    fun setBalance(amount: Double) {
        val newBalance = _accountBalance.value?.toDouble()?.plus(amount) ?: amount
        _accountBalance.value = newBalance
    }
}
