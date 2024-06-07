package com.example.main.fragments.activitybyday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentActivityByDayBinding
import com.example.main.fragments.activitybyday.dialog.DatePickerFragment
import com.example.main.fragments.activitybyday.model.toUi
import com.example.main.fragments.activitybyday.mvi.effect.ActivityByDayEffect
import com.example.main.fragments.activitybyday.mvi.intent.ActivityByDayIntent
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayPartialState
import com.example.main.fragments.activitybyday.mvi.state.ActivityByDayState
import com.example.main.fragments.activitybyday.mvi.store.ActivityByDayStore
import com.example.main.utils.DateConverter
import com.example.main.views.initialize
import com.example.main.views.setCurrentWeight
import com.example.main.views.setSleepTime
import com.example.main.views.setValue
import com.example.main.views.setWaterIntake
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityByDayFragment :
    BaseFragmentMvi<ActivityByDayPartialState, ActivityByDayIntent, ActivityByDayState, ActivityByDayEffect>(
        R.layout.fragment_activity_by_day
    ) {

    private var _binding: FragmentActivityByDayBinding? = null
    private val binding get() = _binding!!

    override val store: ActivityByDayStore by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener(
            DatePickerFragment.REQUEST_KEY_DATE_SELECTED,
            this
        ) { _, bundle ->
            val selectedDate = bundle.getString(DatePickerFragment.BUNDLE_KEY_SELECTED_DATE)
                ?: return@setFragmentResultListener
            store.postIntent(ActivityByDayIntent.GetActivityWithNutritionByDayIntent(selectedDate))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityByDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            cardNutritionActivityByDay.run {
                initialize()
                root.setOnClickListener { navigateToNutritionFragment() }
            }

            containerCardsActivityByDay.initialize()

            buttonSelectDate.setOnClickListener { showDatePickerDialog() }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        render(store.uiState.value)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: ActivityByDayEffect) {
        when (effect) {
            ActivityByDayEffect.OpenSelectDateDialogEffect -> showDatePickerDialog()
        }
    }

    override fun render(state: ActivityByDayState) {
        binding.run {
            state.selectedDateEntity?.let {
                textViewCurrentDateActivityByDay.text =
                    DateConverter.dateEntityToActivityByDayUi(it)
            }

            val nutritionEntity = state.activityWithNutritionEntity.nutrition
            cardNutritionActivityByDay.setValue(nutritionEntity.toUi())

            val activityEntity = state.activityWithNutritionEntity.activityEntity

            containerCardsActivityByDay.run {
                cardSteps.setValue(activityEntity.stepsNumber)
                cardWater.setWaterIntake(activityEntity.waterIntake)
                cardSleep.setSleepTime(activityEntity.sleepTime)
                cardWeight.setCurrentWeight(activityEntity.weight)
            }
        }
    }

    private fun showDatePickerDialog() {
        DatePickerFragment().show(childFragmentManager, null)
    }

    private fun navigateToNutritionFragment() {
        store.uiState.value.selectedDateEntity?.let { date ->
            val action =
                ActivityByDayFragmentDirections.actionActivityByDayFragmentToNutritionFragment(date)
            findNavController().navigate(action)
        }
    }
}