package com.example.main.navigation

import android.content.Context
import android.content.Intent
import com.example.common.navigation.DeeplinkProcessor
import com.example.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainDeeplinkProcessor @Inject constructor(
    @ApplicationContext private val context: Context
) : DeeplinkProcessor {
    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("/main")
    }

    override fun execute(deeplink: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}