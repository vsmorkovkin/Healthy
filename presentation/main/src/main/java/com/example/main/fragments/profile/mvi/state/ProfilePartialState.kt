package com.example.main.fragments.profile.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.profile.model.UserProfileUi

sealed interface ProfilePartialState : MviPartialState {
    data class ProfileLoaded(val userProfileUi: UserProfileUi) : ProfilePartialState
    data class UserProfileImageChanged(val imageUrl: String?) : ProfilePartialState
}