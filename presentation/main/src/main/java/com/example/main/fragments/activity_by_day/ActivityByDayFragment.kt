package com.example.main.fragments.activity_by_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentActivityByDayBinding
import com.example.main.fragments.activity_by_day.dialog.DatePickerFragment
import com.example.main.fragments.activity_by_day.mvi.effect.ActivityByDayEffect
import com.example.main.fragments.activity_by_day.mvi.intent.ActivityByDayIntent
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activity_by_day.mvi.state.ActivityByDayState
import com.example.main.fragments.activity_by_day.mvi.store.ActivityByDayStore
import com.example.main.views.initialize
import com.example.main.views.setValue
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityByDayFragment :
    BaseFragmentMvi<ActivityByDayPartialState, ActivityByDayIntent, ActivityByDayState, ActivityByDayEffect>(
        R.layout.fragment_activity_by_day
    ) {

    private var _binding: FragmentActivityByDayBinding? = null
    private val binding get() = _binding!!

    override val store: ActivityByDayStore by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityByDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.containerCardsActivityByDay.initialize(requireContext())

        binding.run {
            buttonSelectDate.setOnClickListener {
                store.postIntent(ActivityByDayIntent.OpenSelectDateDialogIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: ActivityByDayEffect) {
        when (effect) {
            ActivityByDayEffect.OpenSelectDateDialogEffect -> DatePickerFragment { date ->
                store.postIntent(ActivityByDayIntent.GetActivityByDayIntent(date))
            }.show(parentFragmentManager, null)
        }
    }

    override fun render(state: ActivityByDayState) {

        val activityUi = state.activityUi ?: return

        binding.run {
            textViewCurrentDateActivityByDay.text = activityUi.date

            containerCardsActivityByDay.run {
                cardSteps.setValue(activityUi.stepsNumber)
                cardWater.setValue(activityUi.waterIntake.toInt())
                cardSleep.setValue(activityUi.sleepTime.toInt())
                cardWeight.setValue(activityUi.weight.toInt())
            }
        }
        
    }

}