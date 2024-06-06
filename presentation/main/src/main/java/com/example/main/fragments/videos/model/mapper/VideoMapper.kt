package com.example.main.fragments.videos.model.mapper

import com.example.main.fragments.videos.model.VideoGroupUi
import com.example.main.fragments.videos.model.VideoUi
import com.example.videos.entity.VideoEntity
import com.example.videos.entity.VideoGroupEntity

fun VideoEntity.toUi(): VideoUi {
    return VideoUi(
        id, title, imagePreviewUrl
    )
}

fun VideoGroupEntity.toUi(): VideoGroupUi {
    return VideoGroupUi(
        title,
        videos.map { it.toUi() }
    )
}