package com.example.main.fragments.watchvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import com.example.main.databinding.FragmentWatchingVideoBinding

class WatchingVideoFragment : Fragment() {
    private var _binding: FragmentWatchingVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchingVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoId = WatchingVideoFragmentArgs.fromBundle(requireArguments()).videoId
        val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$videoId?si=Nfz2CLEiZujfL4c6\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        binding.webViewYoutubePlayer.run {
            loadData(video, "text/html", "utf-8")
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}