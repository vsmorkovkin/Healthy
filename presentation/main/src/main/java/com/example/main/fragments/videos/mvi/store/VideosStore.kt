package com.example.main.fragments.videos.mvi.store

import com.example.common.mvi.MviStore
import com.example.main.fragments.videos.mvi.effect.VideosEffect
import com.example.main.fragments.videos.mvi.intent.VideosIntent
import com.example.main.fragments.videos.mvi.state.VideosPartialState
import com.example.main.fragments.videos.mvi.state.VideosState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosStore @Inject constructor(
    actor: VideosActor,
    reducer: VideosReducer
) : MviStore<VideosPartialState, VideosIntent, VideosState, VideosEffect>(reducer, actor) {
    override fun initialStateCreator(): VideosState {
        return VideosState(emptyList())
    }
}