package com.example.main.fragments.videos.recycler_view.videos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.main.fragments.videos.model.VideoUi

class VideoDiffCallback : DiffUtil.ItemCallback<VideoUi>() {
    override fun areItemsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean {
        return oldItem == newItem
    }

}