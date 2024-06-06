package com.example.main.fragments.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentHomeBinding
import com.example.main.extensions.showToast
import com.example.main.fragments.activity_by_day.model.toUi
import com.example.main.fragments.home.dialogs.AddWaterIntakeDialog
import com.example.main.fragments.home.dialogs.EnterCurrentWeightDialog
import com.example.main.fragments.home.dialogs.EnterSleepTimeDialog
import com.example.main.fragments.home.mvi.effect.HomeEffect
import com.example.main.fragments.home.mvi.intent.HomeIntent
import com.example.main.fragments.home.mvi.state.HomePartialState
import com.example.main.fragments.home.mvi.state.HomeState
import com.example.main.fragments.home.mvi.store.HomeStore
import com.example.main.utils.DateConverter
import com.example.main.views.initialize
import com.example.main.views.setCurrentWeight
import com.example.main.views.setSleepTime
import com.example.main.views.setValue
import com.example.main.views.setWaterIntake
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class HomeFragment :
    BaseFragmentMvi<HomePartialState, HomeIntent, HomeState, HomeEffect>(R.layout.fragment_home),
    SensorEventListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override val store: HomeStore by viewModels()

    private lateinit var sensorManager: SensorManager
    private var stepCounterSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        registerForDialogResults()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        store.postIntent(HomeIntent.GetCurrentDate)
        store.postIntent(HomeIntent.GetActivityWithNutrition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        render(store.uiState.value)
    }

    override fun onResume() {
        super.onResume()
        stepCounterSensor?.also { stepCounter ->
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
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
        binding.run {
            textViewCurrentDate.text = state.currentDate

            cardNutritionHome.setValue(state.nutritionEntity.toUi())

            val stateActivity = state.activityEntity
            containerCardsActivity.run {
                cardSteps.setValue(stateActivity.stepsNumber)
                cardWater.setWaterIntake(stateActivity.waterIntake)
                cardSleep.setSleepTime(stateActivity.sleepTime)
                cardWeight.setCurrentWeight(stateActivity.weight)
            }
        }
    }

    private fun registerForDialogResults() {
        registerForWaterDialogResult()
        registerForSleepDialogResult()
        registerForWeightDialog()
    }

    private fun setupViews() {
        binding.run {
            containerCardsActivity.run {
                initialize()

                cardWater.root.setOnClickListener {
                    AddWaterIntakeDialog().show(childFragmentManager, null)
                }

                cardSleep.root.setOnClickListener {
                    EnterSleepTimeDialog().show(childFragmentManager, null)
                }

                cardWeight.root.setOnClickListener {
                    EnterCurrentWeightDialog().show(childFragmentManager, null)
                }
            }

            cardNutritionHome.run {
                initialize()
                root.setOnClickListener {
                    navigateToNutritionFragment()
                }
            }
        }
    }


    private fun registerForWaterDialogResult() {
        childFragmentManager.setFragmentResultListener(
            AddWaterIntakeDialog.REQUEST_KEY_WATER_INTAKE_ENTERED,
            this
        ) { _, bundle ->
            val waterIntake = bundle.getInt(AddWaterIntakeDialog.BUNDLE_KEY_WATER_INTAKE)
            store.postIntent(HomeIntent.UpdateWaterIntakeInActivity(waterIntake))
        }
    }

    private fun registerForSleepDialogResult() {
        childFragmentManager.setFragmentResultListener(
            EnterSleepTimeDialog.REQUEST_KEY_SLEEP_TIME_ENTERED,
            this
        ) { _, bundle ->
            val bedtime = bundle.getString(EnterSleepTimeDialog.BUNDLE_KEY_BEDTIME) ?: return@setFragmentResultListener
            val wakeupTime = bundle.getString(EnterSleepTimeDialog.BUNDLE_KEY_WAKEUP_TIME) ?: return@setFragmentResultListener
            store.postIntent(HomeIntent.UpdateSleepTimeInActivity(bedtime, wakeupTime))
        }
    }

    private fun registerForWeightDialog() {
        childFragmentManager.setFragmentResultListener(
            EnterCurrentWeightDialog.REQUEST_KEY_CURRENT_WEIGHT_ENTERED,
            this
        ) { _, bundle ->
            val currentWeight = bundle.getFloat(EnterCurrentWeightDialog.BUNDLE_KEY_CURRENT_WEIGHT)
            store.postIntent(HomeIntent.UpdateWeightInActivity(currentWeight))
        }
    }

    private fun navigateToNutritionFragment() {
        val currentDate = Calendar.getInstance().time
        val formattedDate = DateConverter.dateToDateEntity(currentDate)
        val action = HomeFragmentDirections.actionHomeFragmentToNutritionFragment(formattedDate)
        findNavController().navigate(action)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                val totalSteps = event.values[0].toInt()
                store.postIntent(HomeIntent.UpdateStepsInActivity(totalSteps))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
