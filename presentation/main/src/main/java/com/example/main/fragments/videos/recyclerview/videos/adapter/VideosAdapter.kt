package com.example.main.fragments.videos.recyclerview.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main.databinding.CardVideoBinding
import com.example.main.fragments.videos.model.VideoUi

class VideosAdapter(
    private val onVideoClick: (videoId: String) -> Unit
) : ListAdapter<VideoUi, VideosAdapter.VideosViewHolder>(
    VideoDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val binding = CardVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onVideoClick)
    }

    class VideosViewHolder(private val binding: CardVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoUi, onVideoClick: (videoId: String) -> Unit) {
            binding.run {
                textViewVideoTitleCard.text = item.title
                Glide.with(imageViewVideoPreviewSmall.context)
                    .load(item.imagePreviewUrl)
                    .centerCrop()
                    .into(imageViewVideoPreviewSmall)
                root.setOnClickListener {
                    onVideoClick(item.id)
                }
            }
        }
    }
}