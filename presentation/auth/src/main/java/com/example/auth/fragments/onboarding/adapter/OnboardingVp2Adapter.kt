package com.example.auth.fragments.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.auth.fragments.onboarding.pages.OnboardingChatFragment
import com.example.auth.fragments.onboarding.pages.OnboardingEventsFragment
import com.example.auth.fragments.onboarding.pages.OnboardingHomeFragment
import com.example.auth.fragments.onboarding.pages.OnboardingVideoFragment

const val ONBOARDING_PAGES_NUMBER = 4

class OnboardingVp2Adapter(
    parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = ONBOARDING_PAGES_NUMBER

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingHomeFragment()
            1 -> OnboardingVideoFragment()
            2 -> OnboardingEventsFragment()
            else -> OnboardingChatFragment()
        }
    }

}