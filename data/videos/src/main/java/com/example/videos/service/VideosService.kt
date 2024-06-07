package com.example.videos.service

import com.example.videos.entity.VideoGroupEntity
import retrofit2.http.GET

interface VideosService {

    @GET("videoGroups.json")
    suspend fun getVideoGroups(): List<VideoGroupEntity>
}