package com.example.main.fragments.home

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.CardActivityBinding
import com.example.main.databinding.FragmentHomeBinding
import com.example.main.fragments.home.model.CardActivityInitial
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.main.fragments.home.mvi.store.HomeStore
import dagger.hilt.android.AndroidEntryPoint


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

    private fun initializeCardActivity(
        cardActivityBinding: CardActivityBinding,
        cardActivityInitial: CardActivityInitial
    ) {
        cardActivityBinding.run {
            title.text = cardActivityInitial.title
            value.text = cardActivityInitial.initialValue
            progress.max = cardActivityInitial.maxProgress
            valueMeasurement.text = cardActivityInitial.valueMeasurement

            icon.setImageDrawable(cardActivityInitial.icon.apply { setTint(cardActivityInitial.itemColor) })

            val layerDrawable = progress.progressDrawable as LayerDrawable
            layerDrawable.findDrawableByLayerId(android.R.id.progress)
                .setTint(cardActivityInitial.itemColor)

            percentage.isVisible = cardActivityInitial.isPercentageVisible
            percentSign.isVisible = cardActivityInitial.isPercentageVisible
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        store.postIntent(HomeIntent.GetCurrentDate)


        binding.run {
            containerCardsActivity.run {

                val theme = requireContext().theme

                Log.d("Home","dimen=${resources.getInteger(R.integer.card_steps_target_value)}")

                initializeCardActivity(
                    cardSteps, CardActivityInitial(
                        getString(R.string.card_steps_title),
                        getString(R.string.card_steps_initial_value),
                        getString(R.string.card_steps_value_measurement),
                        0,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_steps, theme)!!,
                        resources.getColor(R.color.color_steps, theme),
                        true
                    )
                )

                initializeCardActivity(
                    cardWater, CardActivityInitial(
                        getString(R.string.card_water_title),
                        getString(R.string.card_water_initial_value),
                        getString(R.string.card_water_value_measurement),
                        100,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_water, theme)!!,
                        resources.getColor(R.color.color_water, theme),
                        true
                    )
                )

                initializeCardActivity(
                    cardSleep, CardActivityInitial(
                        getString(R.string.card_sleep_title),
                        getString(R.string.card_sleep_initial_value),
                        getString(R.string.card_sleep_value_measurement),
                        100,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_sleep, theme)!!,
                        resources.getColor(R.color.color_sleep, theme),
                        false
                    )
                )

                initializeCardActivity(
                    cardWeight, CardActivityInitial(
                        getString(R.string.card_weight_title),
                        getString(R.string.card_weight_initial_value),
                        getString(R.string.card_weight_value_measurement),
                        100,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_weight, theme)!!,
                        resources.getColor(R.color.color_weight, theme),
                        false
                    )
                )
                

            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: HomeEffect) {
        TODO("Not yet implemented")
    }

    override fun render(state: HomeState) {
        binding.textViewCurrentDate.text = state.currentDate
    }

}