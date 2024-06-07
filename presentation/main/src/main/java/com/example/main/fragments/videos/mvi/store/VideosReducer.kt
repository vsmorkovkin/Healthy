package com.example.main.fragments.videos.mvi.store

import com.example.common.mvi.MviReducer
import com.example.main.fragments.videos.mvi.state.VideosPartialState
import com.example.main.fragments.videos.mvi.state.VideosState
import javax.inject.Inject

class VideosReducer @Inject constructor() : MviReducer<VideosPartialState, VideosState> {
    override fun reduce(prevState: VideosState, partialState: VideosPartialState): VideosState {
        return when (partialState) {
            is VideosPartialState.VideoGroupsLoaded -> prevState.copy(videoGroups = partialState.videoGroups)
        }
    }
}