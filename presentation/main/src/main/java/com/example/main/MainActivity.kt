package com.example.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.main.databinding.ActivityMainBinding
import com.example.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

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
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.videosFragment, R.id.eventsFragment, R.id.chatsFragment)
        )
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.profileFragment) {
                binding.imageViewProfile.isVisible = false
                binding.bottomNavViewMain.isVisible = false
            } else {
                binding.imageViewProfile.isVisible = true
                binding.bottomNavViewMain.isVisible = true
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
}