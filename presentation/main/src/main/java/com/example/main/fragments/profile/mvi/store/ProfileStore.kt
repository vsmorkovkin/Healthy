package com.example.main.fragments.profile.mvi.store

import com.example.common.mvi.MviStore
import com.example.main.fragments.profile.mvi.effect.ProfileEffect
import com.example.main.fragments.profile.mvi.intent.ProfileIntent
import com.example.main.fragments.profile.mvi.state.ProfilePartialState
import com.example.main.fragments.profile.mvi.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileStore @Inject constructor(
    actor: ProfileActor,
    reducer: ProfileReducer,
) : MviStore<ProfilePartialState, ProfileIntent, ProfileState, ProfileEffect>(reducer, actor) {
    override fun initialStateCreator(): ProfileState {
        return ProfileState(null)
    }
}