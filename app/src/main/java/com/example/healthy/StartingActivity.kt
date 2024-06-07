package com.example.healthy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.auth.AuthorizationActivity
import com.example.common.navigation.DeeplinkHandler
import com.example.main.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class StartingActivity : Activity() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface StartingActivityEntryPoint {
        fun deeplinkHandler(): DeeplinkHandler
    }

    private lateinit var deeplinkHandler: DeeplinkHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContentView(R.layout.activity_starting)

        // Inject dependency
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(
                applicationContext,
                StartingActivityEntryPoint::class.java
            )
        deeplinkHandler = hiltEntryPoint.deeplinkHandler()

        // Handle intent
        intent?.let {
            val navigationIntent = handleIntent(it)
            if (navigationIntent) return
        }

        resolveSession()
    }

    private fun handleIntent(intent: Intent): Boolean {
        var isNavigationIntent = false
        intent.data?.toString()?.let {
            isNavigationIntent = deeplinkHandler.process(it)
            finish()
        }
        return isNavigationIntent
    }

    private fun resolveSession() {
        val currentUser = Firebase.auth.currentUser
        val intent = if (currentUser == null) {
            Intent(baseContext, AuthorizationActivity::class.java)
        } else {
            Intent(baseContext, MainActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}