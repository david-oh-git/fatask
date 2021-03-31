/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.davidosemwota.authentication.AuthenticationGraph
import io.davidosemwota.authentication.data.SignInResult
import io.davidosemwota.authentication.usecase.SignInUseCase
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val signInUseCase: SignInUseCase = AuthenticationGraph.signInUseCase
) : ViewModel() {

    private val _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState>
        get() = _signInState

    fun signIn(idToken: String) = viewModelScope.launch {
        _signInState.postValue(SignInState.Progress)

        val signInResult = signInUseCase.execute(idToken)
        _signInState.postValue(
            when (signInResult) {
                SignInResult.Error -> SignInState.Error
                SignInResult.Success -> SignInState.Success
            }
        )
    }
}
