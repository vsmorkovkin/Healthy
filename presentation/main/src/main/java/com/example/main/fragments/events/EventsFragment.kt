package com.example.main.fragments.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.main.databinding.FragmentEventsBinding
import com.example.main.fragments.events.adapter.EventsFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2Events.adapter = EventsFragmentAdapter(this)
        binding.viewPager2Events.isUserInputEnabled = false



        val titles = arrayOf("Мои события", "Все события")

        TabLayoutMediator(
            binding.tabLayoutEvents,
            binding.viewPager2Events
        ) { tab, index -> tab.text = titles[index] }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}