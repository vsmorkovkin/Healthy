package com.example.main.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentHomeBinding
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.main.fragments.home.mvi.store.HomeStore
import com.example.main.utils.DateConverter
import com.example.main.views.initialize
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class HomeFragment :
    BaseFragmentMvi<HomePartialState, HomeIntent, HomeState, HomeEffect>(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override val store: HomeStore by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        store.postIntent(HomeIntent.GetCurrentDate)

        binding.run {
            containerCardsActivity.initialize(requireContext())
            cardNutritionHome.run {
                initialize()
                root.setOnClickListener {
                    navigateToNutritionFragment()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: HomeEffect) {
        when (effect) {
            HomeEffect.NavigateToNutritionFragment -> {}
        }
    }

    override fun render(state: HomeState) {
        binding.textViewCurrentDate.text = state.currentDate
    }

    private fun navigateToNutritionFragment() {
        val currentDate = Calendar.getInstance().time
        val formattedDate = DateConverter.dateToHomeUi(currentDate)
        val action = HomeFragmentDirections.actionHomeFragmentToNutritionFragment(formattedDate)
        findNavController().navigate(action)
    }

}