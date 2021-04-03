/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.payment.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import io.osemwota.utility.DataStoreSource
import io.osemwota.utility.SAVE_NAME_KEY
import io.osemwota.utility.UtilityGraph

class HomeViewModel(
    application: Application,
    dataStoreSource: DataStoreSource
) : AndroidViewModel(application) {

    private val generateRandomBalance
        get() = listOf(0.0, 27.67, 500.00, 7450.20).random()
    private val _accountBalance = MutableLiveData(generateRandomBalance)
    val accountBalance: LiveData<Double> = _accountBalance
    val name: LiveData<String> = dataStoreSource.read(
        application.applicationContext,
        SAVE_NAME_KEY
    ).asLiveData()

    fun setBalance(amount: Double) {
        val newBalance = _accountBalance.value?.toDouble()?.plus(amount) ?: amount
        _accountBalance.value = newBalance
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val application: Application,
    private val dataStoreSource: DataStoreSource = UtilityGraph
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(application, dataStoreSource) as T
}
