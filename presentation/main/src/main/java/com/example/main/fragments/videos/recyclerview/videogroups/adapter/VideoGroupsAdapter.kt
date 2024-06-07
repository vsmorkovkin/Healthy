package com.example.main.fragments.videos.recyclerview.videogroups.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import com.example.main.databinding.CardVideoGroupBinding
import com.example.main.fragments.videos.model.VideoGroupUi
import com.example.main.fragments.videos.recyclerview.videos.adapter.VideosAdapter
import com.example.main.fragments.videos.recyclerview.videos.decoration.VideoItemDecoration

class VideoGroupsAdapter(
    private val onVideoClick: (videoId: String) -> Unit
) :
    ListAdapter<VideoGroupUi, VideoGroupsAdapter.VideoGroupsViewHolder>(
        VideoGroupDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGroupsViewHolder {
        val binding = CardVideoGroupBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoGroupsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoGroupsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onVideoClick)
    }

    class VideoGroupsViewHolder(private val binding: CardVideoGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context
        private val dimen12dp = context.resources.getDimension(R.dimen._12dp).toInt()
        private val videoItemDecoration = VideoItemDecoration(dimen12dp, 0)

        fun bind(item: VideoGroupUi, onVideoClick: (videoId: String) -> Unit) {
            binding.run {
                textViewVideoGroupTitle.text = item.title

                val adapter = VideosAdapter(onVideoClick)
                recyclerViewVideos.adapter = adapter
                recyclerViewVideos.addItemDecoration(videoItemDecoration)
                adapter.submitList(item.videos)
            }
        }
    }
}