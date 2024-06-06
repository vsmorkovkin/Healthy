package com.example.main.fragments.videos.mvi.store

import com.example.common.mvi.MviActor
import com.example.main.fragments.videos.model.VideoGroupUi
import com.example.main.fragments.videos.model.VideoUi
import com.example.main.fragments.videos.mvi.effect.VideosEffect
import com.example.main.fragments.videos.mvi.intent.VideosIntent
import com.example.main.fragments.videos.mvi.state.VideosPartialState
import com.example.main.fragments.videos.mvi.state.VideosState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideosActor @Inject constructor() :
    MviActor<VideosPartialState, VideosIntent, VideosState, VideosEffect>() {
    override fun resolve(intent: VideosIntent, state: VideosState): Flow<VideosPartialState> {
        return when(intent) {
            VideosIntent.GetVideoGroups -> getVideoGroups()
        }
    }

    private fun getVideoGroups(): Flow<VideosPartialState> = flow {
        val videoGroups = Array(5) {
            VideoGroupUi(
                "Видео группа $it",
                Array(7) { videoId ->
                    VideoUi(
                        ((it + 1)*(videoId + 1)).toString(),
                        "Видео $videoId",
                        ""
                    )
                }.toMutableList()
            )
        }.toMutableList()

        emit(VideosPartialState.VideoGroupsLoaded(videoGroups))
    }
}