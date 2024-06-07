package com.example.main.fragments.events.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.main.fragments.events.allevents.AllEventsFragment
import com.example.main.fragments.events.userevents.UserEventsFragment

class EventsFragmentAdapter(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserEventsFragment()
            else -> AllEventsFragment()
        }
    }
}