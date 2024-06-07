package com.example.main.fragments.profile.mvi.state

import com.example.common.mvi.MviState
import com.example.main.fragments.profile.model.UserProfileUi

data class ProfileState(
    val userProfileUi: UserProfileUi?
) : MviState
