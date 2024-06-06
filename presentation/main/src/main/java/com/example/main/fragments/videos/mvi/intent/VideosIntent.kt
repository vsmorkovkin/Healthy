package com.example.main.fragments.videos.mvi.intent

import com.example.common.mvi.MviIntent

sealed interface VideosIntent : MviIntent {
    data object GetVideoGroups : VideosIntent
}