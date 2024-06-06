package com.example.videos.repository

import com.example.videos.entity.VideoGroupEntity
import com.example.videos.service.VideosService
import javax.inject.Inject

class VideosRepositoryImpl @Inject constructor(
    private val videosService: VideosService
) : VideosRepository {

    override suspend fun getVideoGroups(): List<VideoGroupEntity> {
        return videosService.getVideoGroups()
    }
}