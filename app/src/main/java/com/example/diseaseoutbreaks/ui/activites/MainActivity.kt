package com.example.diseaseoutbreaks.ui.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**
         *Bottom Navigation with action bar
         * */
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(
                item, Navigation.findNavController(this,
                    R.id.nav_host_fragment
                )
            )
        }

        val navController = this.findNavController(R.id.nav_host_fragment)

        binding.bottomNavigation.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.diseaseOutbreaks,
                R.id.productAlert,
                R.id.maternal,
                R.id.emergency
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

}

