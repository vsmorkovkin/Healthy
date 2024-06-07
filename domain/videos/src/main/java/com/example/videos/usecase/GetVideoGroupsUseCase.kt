package com.example.videos.usecase

import com.example.videos.entity.VideoGroupEntity
import com.example.videos.repository.VideosRepository

class GetVideoGroupsUseCase(
    private val videosRepository: VideosRepository
) {
    suspend operator fun invoke(): List<VideoGroupEntity> {
        return videosRepository.getVideoGroups()
    }
}