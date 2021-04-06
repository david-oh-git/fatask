/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import io.osemwota.authentication.R
import io.osemwota.authentication.databinding.FragmentAuthenticationBinding
import io.osemwota.utility.extentions.makeGone
import io.osemwota.utility.extentions.makeVisible
import io.osemwota.utility.extentions.observe
import io.osemwota.utility.extentions.sendSnack

class AuthenticationFragment : Fragment() {

    private val viewModel by viewModels<AuthenticationViewModel>()
    private lateinit var binding: FragmentAuthenticationBinding
    private val googleSignInObserver = GoogleSignInResultObserver(
        fragment = this,
        onError = ::handleGoogleSignInError,
        onSuccess = { context, key, value -> viewModel.saveName(context, key, value) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(googleSignInObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInWithGoogleButton.setOnClickListener {
            viewModel.setSignInState(SignInState.Progress)
            googleSignInObserver.signIn()
        }
        observe(viewModel.signInState, ::handleSignInState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthenticationBinding.inflate(inflater)

        return binding.root
    }

    private fun handleGoogleSignInError(exception: ApiException) {
        val message = if (exception.statusCode == CommonStatusCodes.NETWORK_ERROR) {
            getString(R.string.error_no_network)
        } else {
            getString(R.string.authorization_sign_in_with_google_unknown_error)
        }
        viewModel.setSignInState(SignInState.Error)
        sendSnack(binding.container, message)
    }

    private fun handleSignInState(signInState: SignInState) {
        when (signInState) {
            is SignInState.Success -> {
                sendSnack(
                    binding.container,
                    getString(R.string.authorization_successful_sign_in)
                )
                findNavController().navigateUp()
            }
            is SignInState.Error -> {
                binding.progressBar.makeGone()
                binding.signInWithGoogleButton.isEnabled = true
                sendSnack(
                    binding.container,
                    getString(R.string.authorization_sign_in_with_google_unknown_error)
                )
            }
            is SignInState.Progress -> {
                binding.progressBar.makeVisible()
                binding.signInWithGoogleButton.isEnabled = false
            }
        }
    }
}
