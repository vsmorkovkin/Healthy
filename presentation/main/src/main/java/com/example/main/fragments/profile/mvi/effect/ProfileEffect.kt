package com.example.main.fragments.profile.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface ProfileEffect : MviEffect {
    data class GetUserProfileFailure(val message: String) : ProfileEffect

    data object OpenSelectImageDialog: ProfileEffect
    data class SetUserProfileImageFailure(val message: String) : ProfileEffect
    data object UserProfileImageChanged : ProfileEffect

    data object SuccessfulLogout : ProfileEffect

    data class LogoutFailure(val message: String) : ProfileEffect
}