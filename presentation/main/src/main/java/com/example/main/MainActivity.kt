package com.example.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.main.databinding.ActivityMainBinding
import com.example.main.fragments.profile.ProfileFragment
import com.example.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationSetup()
        registerFragmentResult()

        mainViewModel.userProfileUrl.onEach {
            if (it == null) return@onEach

            binding.run {
                Glide.with(imageViewProfile.context)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(imageViewProfile)
            }
        }.launchIn(lifecycleScope)

        mainViewModel.getUserProfile()

        binding.imageViewProfile.setOnClickListener {
            navController.navigate(
                R.id.profileFragment,
                null,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.profileFragment, true)
                    .build()
            )
        }
    }

    private fun registerFragmentResult() {
        navHostFragment.childFragmentManager.setFragmentResultListener(
            ProfileFragment.REQUEST_KEY_CHANGED_PROFILE_IMAGE,
            this
        ) { _, _ ->
            Glide.get(binding.imageViewProfile.context).clearMemory()
            mainViewModel.getUserProfile()
        }
    }

    private fun navigationSetup() {
        // Get navigation controller
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Bottom navigation bar setup
        binding.bottomNavViewMain.setupWithNavController(navController)

        // Toolbar setup
        setSupportActionBar(binding.toolbarMain)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.videosFragment, R.id.eventsFragment, R.id.chatsFragment)
        )
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, args ->
            val bottomNavViewItems = listOf(
                R.id.homeFragment,
                R.id.videosFragment,
                R.id.eventsFragment,
                R.id.chatsFragment
            )

            binding.run {
                // hide profile image if destination is not in bottomNavViews
                (destination.id in bottomNavViewItems).let {
                    imageViewProfile.isVisible = it
                }

                // hide bottom nav view in ProfileFragment
                bottomNavViewMain.isVisible = destination.id != R.id.profileFragment

                toolbarMain.menu.findItem(R.id.activityByDayFragment)?.isVisible =
                    destination.id == R.id.homeFragment
            }
        }

        // Set toolbar title with current destination label instead of app name
        navController.currentDestination?.let {
            supportActionBar?.title = it.label
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