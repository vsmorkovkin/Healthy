package com.example.main.fragments.videos.mvi.state

import com.example.common.mvi.MviState
import com.example.main.fragments.videos.model.VideoGroupUi

data class VideosState(
    val videoGroups: List<VideoGroupUi>
) : MviState