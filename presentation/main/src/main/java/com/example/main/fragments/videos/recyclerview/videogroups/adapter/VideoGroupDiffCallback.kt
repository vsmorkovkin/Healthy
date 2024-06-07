package com.example.main.fragments.videos.recyclerview.videogroups.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.main.fragments.videos.model.VideoGroupUi

class VideoGroupDiffCallback : DiffUtil.ItemCallback<VideoGroupUi>() {
    override fun areItemsTheSame(oldItem: VideoGroupUi, newItem: VideoGroupUi): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: VideoGroupUi, newItem: VideoGroupUi): Boolean {
        return oldItem == newItem
    }
}