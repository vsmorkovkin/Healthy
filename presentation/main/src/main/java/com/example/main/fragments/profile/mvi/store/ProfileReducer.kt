package com.example.main.fragments.profile.mvi.store

import android.net.Uri
import com.example.common.mvi.MviReducer
import com.example.main.fragments.profile.mvi.state.ProfilePartialState
import com.example.main.fragments.profile.mvi.state.ProfileState
import javax.inject.Inject

class ProfileReducer @Inject constructor() : MviReducer<ProfilePartialState, ProfileState> {
    override fun reduce(prevState: ProfileState, partialState: ProfilePartialState): ProfileState {
        return when (partialState) {
            is ProfilePartialState.ProfileLoaded -> prevState.copy(userProfileUi = partialState.userProfileUi)
            is ProfilePartialState.UserProfileImageChanged -> ProfileState(
                userProfileUi = prevState.userProfileUi!!.copy(imageUrl = Uri.parse(partialState.imageUrl))
            )
        }
    }
}