package com.example.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.main.databinding.ActivityMainBinding
import com.example.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

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
        setSupportActionBar(binding.toolbarMain)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.videosFragment, R.id.eventsFragment, R.id.chatsFragment)
        )
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val bottomNavViewItems = listOf(
                R.id.homeFragment,
                R.id.videosFragment,
                R.id.eventsFragment,
                R.id.chatsFragment
            )

            binding.run {
                (destination.id in bottomNavViewItems).let {
                    imageViewProfile.isVisible = it
                    bottomNavViewMain.isVisible = it
                }

                toolbarMain.menu.findItem(R.id.activityByDayFragment)?.isVisible =
                    destination.id == R.id.homeFragment
            }

        }

        mainViewModel.userProfileUrl.onEach {
            binding.run {
                Glide.with(imageViewProfile.context)
                    .load(it)
                    .centerCrop()
                    .into(imageViewProfile)
            }
        }.launchIn(lifecycleScope)

        mainViewModel.getUserProfile()

        binding.imageViewProfile.setOnClickListener {
            navController.navigate(R.id.profileFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.main_nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}