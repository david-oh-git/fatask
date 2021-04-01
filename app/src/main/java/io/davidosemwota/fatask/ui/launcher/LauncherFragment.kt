/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.fatask.ui.launcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class LauncherFragment : Fragment() {

    private val viewModel by viewModels<LauncherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isUserAuthenticated.observe(
            this,
            { isUserAuthenticated ->
                val action = LauncherFragmentDirections.navigateFromLauncherToNotAuthenticatedGraph()

                findNavController().navigate(action)
            }
        )
    }
}
