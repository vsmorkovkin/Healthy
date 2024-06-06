package com.example.main.fragments.videos.recycler_view.videogroups.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import com.example.main.databinding.CardVideoGroupBinding
import com.example.main.fragments.videos.model.VideoGroupUi
import com.example.main.fragments.videos.recycler_view.videos.adapter.VideosAdapter
import com.example.main.fragments.videos.recycler_view.videos.decoration.VideoItemDecoration


class VideoGroupsAdapter :
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
        holder.bind(item)
    }

    class VideoGroupsViewHolder(private val binding: CardVideoGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context
        private val dimen12dp = context.resources.getDimension(R.dimen._12dp).toInt()
        private val videoItemDecoration = VideoItemDecoration(dimen12dp, 0)

        fun bind(item: VideoGroupUi) {
            binding.run {
                textViewVideoGroupTitle.text = item.title

                val adapter = VideosAdapter()
                recyclerViewVideos.adapter = adapter
                recyclerViewVideos.addItemDecoration(videoItemDecoration)
                adapter.submitList(item.videos)
            }

        }
    }

}