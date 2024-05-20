package com.example.auth.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.auth.databinding.FragmentOnboardingBinding
import com.example.auth.onboarding.adapter.ONBOARDING_PAGES_NUMBER
import com.example.auth.onboarding.adapter.OnboardingVp2Adapter
import com.google.android.material.tabs.TabLayoutMediator


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val vp2Adapter by lazy(LazyThreadSafetyMode.NONE) { OnboardingVp2Adapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

            viewPager2Onboarding.run {
                adapter = vp2Adapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position == ONBOARDING_PAGES_NUMBER - 1) {
                            buttonOnboardingForward.isVisible = false
                            buttonOnboardingGetStarted.isVisible = true
                        } else {
                            buttonOnboardingForward.isVisible = true
                            buttonOnboardingGetStarted.isVisible = false
                        }
                    }
                })
            }

            buttonOnboardingForward.setOnClickListener {
                viewPager2Onboarding.currentItem = viewPager2Onboarding.currentItem + 1
            }

            TabLayoutMediator(
                tabLayoutPageIndicator,
                viewPager2Onboarding,
            ) { _, _ -> run {} }.attach()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}