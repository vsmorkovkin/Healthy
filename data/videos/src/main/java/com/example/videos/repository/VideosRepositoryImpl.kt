package com.example.videos.repository

import com.example.videos.entity.VideoEntity
import com.example.videos.entity.VideoGroupEntity
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor() : VideosRepository {

    override suspend fun getVideoGroups(): List<VideoGroupEntity> {
        val videoGroups = Array(5) {
            VideoGroupEntity(
                "Видео группа $it",
                Array(7) { videoId ->
                    VideoEntity(
                        ((it + 1) * (videoId + 1)).toString(),
                        "Видео $videoId",
                        ""
                    )
                }.toMutableList()
            )
        }.toMutableList()

        return videoGroups
    }
}