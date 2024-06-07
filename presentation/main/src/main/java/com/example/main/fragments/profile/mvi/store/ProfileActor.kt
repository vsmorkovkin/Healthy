package com.example.main.fragments.profile.mvi.store

import com.example.common.mvi.MviActor
import com.example.main.fragments.profile.model.toUi
import com.example.main.fragments.profile.mvi.effect.ProfileEffect
import com.example.main.fragments.profile.mvi.intent.ProfileIntent
import com.example.main.fragments.profile.mvi.state.ProfilePartialState
import com.example.main.fragments.profile.mvi.state.ProfileState
import com.example.session.usecase.GetUserProfileUseCase
import com.example.session.usecase.LogoutUseCase
import com.example.session.usecase.SetUserProfileImageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileActor @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val setUserProfileImageUseCase: SetUserProfileImageUseCase,
    private val logoutUseCase: LogoutUseCase
) :
    MviActor<ProfilePartialState, ProfileIntent, ProfileState, ProfileEffect>() {
    override fun resolve(intent: ProfileIntent, state: ProfileState): Flow<ProfilePartialState> {
        return when (intent) {
            ProfileIntent.GetUserProfile -> getUserProfile()
            ProfileIntent.SelectUserProfileImageFromDevice -> flow { _effects.emit(ProfileEffect.OpenSelectImageDialog) }
            is ProfileIntent.SetUserProfileImage -> setUserProfileImage(intent.imageUri)
            ProfileIntent.PerformLogout -> performLogout()
        }
    }

    private fun getUserProfile(): Flow<ProfilePartialState> = flow {
        runCatching {
            getUserProfileUseCase()
        }.fold(
            onSuccess = {
                emit(ProfilePartialState.ProfileLoaded(it.toUi()))
            },
            onFailure = {
                _effects.emit(ProfileEffect.GetUserProfileFailure(it.message.orEmpty()))
            }
        )
    }

    private fun setUserProfileImage(imageUri: String): Flow<ProfilePartialState> = flow {
        runCatching {
            setUserProfileImageUseCase(imageUri)
        }.fold(
            onSuccess = {
                emit(ProfilePartialState.UserProfileImageChanged(it))
                _effects.emit(ProfileEffect.UserProfileImageChanged)
            },
            onFailure = {
                _effects.emit(ProfileEffect.SetUserProfileImageFailure(it.message.orEmpty()))
            }
        )
    }

    private fun performLogout(): Flow<ProfilePartialState> = flow {
        runCatching {
            logoutUseCase()
        }.fold(
            onSuccess = {
                _effects.emit(ProfileEffect.SuccessfulLogout)
            },
            onFailure = {
                _effects.emit(ProfileEffect.LogoutFailure(it.message.orEmpty()))
            }
        )
    }
}