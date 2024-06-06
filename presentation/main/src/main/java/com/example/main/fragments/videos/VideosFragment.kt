package com.example.main.fragments.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentVideosBinding
import com.example.main.fragments.videos.mvi.effect.VideosEffect
import com.example.main.fragments.videos.mvi.intent.VideosIntent
import com.example.main.fragments.videos.mvi.state.VideosPartialState
import com.example.main.fragments.videos.mvi.state.VideosState
import com.example.main.fragments.videos.mvi.store.VideosStore
import com.example.main.fragments.videos.recycler_view.videogroups.adapter.VideoGroupsAdapter
import com.example.main.fragments.videos.recycler_view.videogroups.decoration.VideoGroupItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideosFragment : BaseFragmentMvi<VideosPartialState, VideosIntent, VideosState, VideosEffect>(
    R.layout.fragment_videos
) {

    private var _binding: FragmentVideosBinding? = null
    private val binding get() = _binding!!

    override val store: VideosStore by viewModels()

    private val videosAdapter by lazy(LazyThreadSafetyMode.NONE) {
        VideoGroupsAdapter {
            val action = VideosFragmentDirections.actionVideosFragmentToWatchingVideoFragment(it)
            findNavController().navigate(action)
        }
    }

    private val videoGroupItemDecoration by lazy(LazyThreadSafetyMode.NONE) {
        val dimen16dp = resources.getDimension(R.dimen._16dp).toInt()
        VideoGroupItemDecoration(dimen16dp, dimen16dp, dimen16dp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewVideoGroups.adapter = videosAdapter
        binding.recyclerViewVideoGroups.addItemDecoration(videoGroupItemDecoration)

        store.postIntent(VideosIntent.GetVideoGroups)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: VideosEffect) {}

    override fun render(state: VideosState) {
        videosAdapter.submitList(state.videoGroups)
    }

}