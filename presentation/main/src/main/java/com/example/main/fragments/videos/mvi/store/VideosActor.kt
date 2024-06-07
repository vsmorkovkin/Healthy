package com.example.main.fragments.videos.mvi.store

import android.util.Log
import com.example.common.mvi.MviActor
import com.example.main.fragments.videos.model.mapper.toUi
import com.example.main.fragments.videos.mvi.effect.VideosEffect
import com.example.main.fragments.videos.mvi.intent.VideosIntent
import com.example.main.fragments.videos.mvi.state.VideosPartialState
import com.example.main.fragments.videos.mvi.state.VideosState
import com.example.videos.usecase.GetVideoGroupsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideosActor @Inject constructor(
    private val getVideoGroupsUseCase: GetVideoGroupsUseCase
) :
    MviActor<VideosPartialState, VideosIntent, VideosState, VideosEffect>() {

    override fun resolve(intent: VideosIntent, state: VideosState): Flow<VideosPartialState> {
        return when (intent) {
            VideosIntent.GetVideoGroups -> getVideoGroups()
        }
    }

    private fun getVideoGroups(): Flow<VideosPartialState> = flow {
        runCatching {
            getVideoGroupsUseCase()
        }.fold(
            onSuccess = { videoGroupEntities ->
                val videoGroupsUi = videoGroupEntities.map { it.toUi() }
                emit(VideosPartialState.VideoGroupsLoaded(videoGroupsUi))
            },
            onFailure = {
                Log.d("Videos", "getVideoGroups failure: ${it.message}")
            }
        )
    }
}