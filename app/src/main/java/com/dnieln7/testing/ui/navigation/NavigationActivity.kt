package com.dnieln7.testing.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.dnieln7.testing.R
import com.dnieln7.testing.databinding.ActivityNavigationBinding
import com.dnieln7.testing.model.keyboard.KeySet

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val host = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val controller = host.navController

        controller.addOnDestinationChangedListener(listener)

        appBarConfiguration = AppBarConfiguration(controller.graph)

        setupActionBarWithNavController(controller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private val listener = NavController.OnDestinationChangedListener { _, destination, arguments ->
        when (destination.id) {
            R.id.keysFragment -> {
                binding.toolbar.subtitle = (arguments?.get("keySet") as KeySet?)?.name ?: ""
            }
            else -> binding.toolbar.subtitle = ""
        }
    }
}