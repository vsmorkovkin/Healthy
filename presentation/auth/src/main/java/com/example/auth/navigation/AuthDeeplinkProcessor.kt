package com.example.auth.navigation

import android.content.Context
import android.content.Intent
import com.example.auth.AuthorizationActivity
import com.example.common.navigation.DeeplinkProcessor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDeeplinkProcessor @Inject constructor(
    @ApplicationContext private val context: Context
) : DeeplinkProcessor {

    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("/auth")
    }

    override fun execute(deeplink: String) {
        val intent = Intent(context, AuthorizationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}