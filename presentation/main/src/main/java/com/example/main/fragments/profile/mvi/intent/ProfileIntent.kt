package com.example.main.fragments.profile.mvi.intent

import com.example.common.mvi.MviIntent

sealed interface ProfileIntent : MviIntent {
    data object GetUserProfile: ProfileIntent
    data object SelectUserProfileImageFromDevice: ProfileIntent
    data class SetUserProfileImage(val imageUri: String): ProfileIntent
    data object PerformLogout: ProfileIntent
}