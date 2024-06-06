package com.example.main.fragments.videos.mvi.state

import com.example.common.mvi.MviPartialState
import com.example.main.fragments.videos.model.VideoGroupUi

sealed interface VideosPartialState : MviPartialState {
    data class VideoGroupsLoaded(val videoGroups: List<VideoGroupUi>) : VideosPartialState
}