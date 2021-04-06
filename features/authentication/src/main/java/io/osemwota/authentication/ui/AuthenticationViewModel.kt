/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.osemwota.utility.DataStoreSource
import io.osemwota.utility.UtilityGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val dataStoreSource: DataStoreSource = UtilityGraph
) : ViewModel() {

    private val _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState>
        get() = _signInState

    fun setSignInState(state: SignInState) {
        _signInState.postValue(state)
    }

    fun saveName(context: Context, key: String, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreSource.save(context, key, value)
            setSignInState(SignInState.Success)
        }
    }
}
