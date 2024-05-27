package com.example.common.navigation

import android.content.Context
import android.net.Uri
import com.example.common.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NavigationDeeplinkContainer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val navigationDeeplinkBase: String by lazy {
        val scheme = context.getString(R.string.navigation_deeplink_scheme)
        val host = context.getString(R.string.navigation_deeplink_host)
        "${scheme}://$host"
    }

    val DEEPLINK_TO_MAIN_ACTIVITY: Uri by lazy { createDeeplink(R.string.path_to_main_activity) }

    val DEEPLINK_TO_AUTH_ACTIVITY: Uri by lazy { createDeeplink(R.string.path_to_authorization_activity) }

    private fun createDeeplink(pathStringId: Int): Uri {
        val path = context.getString(pathStringId)
        return Uri.parse("$navigationDeeplinkBase$path")
    }

}