package com.example.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Bottom navigation bar setup
        binding.bottomNavViewMain.setupWithNavController(navController)

        // Toolbar setup
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.videosFragment, R.id.eventsFragment, R.id.chatsFragment)
        )
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            /*if (destination.id == R.id.full_screen_destination) {
                toolbar.visibility = View.GONE
                bottomNavigationView.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
                bottomNavigationView.visibility = View.VISIBLE
            }*/
        }



    }
}