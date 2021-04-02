/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.authentication.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            viewModel.setSigninState(SignInState.Success)
        } else {
            viewModel.setSigninState(SignInState.Error)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInWithGoogleButton.setOnClickListener { signIn() }
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

    private fun signIn() {
        viewModel.setSigninState(SignInState.Progress)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        resultLauncher.launch(googleSignInClient.signInIntent)
    }

    fun isUserSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        return account != null
    }
}
