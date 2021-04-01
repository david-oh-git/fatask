/*
 *
 * Developed by David Osemwota(david-oh-git) (c) 2021
 *
 */
package io.davidosemwota.fatask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import io.davidosemwota.fatask.databinding.ActivityMainBinding
import io.davidosemwota.fatask.navigation.RootNavigator

class MainActivity : AppCompatActivity(), RootNavigator {

    override val navController: NavController
        get() = findNavController(R.id.fragmentContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
