package com.example.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.onboarding.usecase.GetOnboardingCompleteStatusUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {

    @Inject
    lateinit var getOnboardingCompleteStatusUseCase: GetOnboardingCompleteStatusUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)


        if (getOnboardingCompleteStatusUseCase()) {
            // if onboarding was completed navigate to LoginFragment
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
            val navController: NavController = navHostFragment.navController

            val navOptions = navOptions {
                popUpTo(R.id.welcomeFragment) { inclusive = true }
            }
            navController.navigate(R.id.loginFragment, null, navOptions)
        }

    }
}