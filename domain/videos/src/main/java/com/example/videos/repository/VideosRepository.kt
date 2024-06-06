package com.example.videos.repository

import com.example.videos.entity.VideoGroupEntity

interface VideosRepository {
    suspend fun getVideoGroups(): List<VideoGroupEntity>
}