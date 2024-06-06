package com.example.main.fragments.videos.recycler_view.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main.databinding.CardVideoBinding
import com.example.main.fragments.videos.model.VideoUi

class VideosAdapter : ListAdapter<VideoUi, VideosAdapter.VideosViewHolder>(
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
        holder.bind(item)
    }

    class VideosViewHolder(private val binding: CardVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoUi) {
            binding.run {
                textViewVideoTitleCard.text = item.title
                Glide.with(imageViewVideoPreviewSmall.context)
                    .load(item.imagePreviewUrl)
                    .centerCrop()
                    .into(imageViewVideoPreviewSmall)
            }

        }
    }

}