package com.example.main.fragments.events.userevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.events.entity.EventEntity
import com.example.main.R
import com.example.main.databinding.FragmentUserEventsBinding
import com.example.main.fragments.events.userevents.recyclerview.adapter.UserEventsAdapter
import com.example.main.fragments.nutrition.recyclerview.decoration.MealItemDecoration
import com.example.main.fragments.videos.recyclerview.videos.decoration.VideoItemDecoration

class UserEventsFragment : Fragment() {
    private var _binding: FragmentUserEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserEventsAdapter()
        binding.recyclerViewUserEvents.adapter = adapter
        val dimen16dp = resources.getDimension(R.dimen._16dp).toInt()
        binding.recyclerViewUserEvents.addItemDecoration(
            MealItemDecoration(dimen16dp, dimen16dp, dimen16dp)
        )
        adapter.submitList(Array(5) {
            EventEntity(
                it.toString(),
                "Событие $it",
                (it + 1) * 3,
                "chatId$it",
                it * 1000L
            )
        }.toMutableList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}