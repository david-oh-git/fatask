/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.osemwota.fatask.ui.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn

class LauncherFragment : Fragment() {

    private val viewModel by viewModels<LauncherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkIfUserIsAuthenticated = ::isUserSignedIn
        viewModel.isUserAuthenticated.observe(
            this,
            { isUserAuthenticated ->
                val action = if (isUserAuthenticated) {
                    LauncherFragmentDirections
                        .navigateFromLauncherToAuthenticatedGraph()
                } else {
                    LauncherFragmentDirections
                        .navigateFromLauncherToNotAuthenticatedGraph()
                }

                findNavController().navigate(action)
            }
        )
    }

    private fun isUserSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        return account != null
    }
}
