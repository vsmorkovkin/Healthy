package com.example.main.fragments.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentNutritionBinding
import com.example.main.fragments.nutrition.dialog.AddMealDialog
import com.example.main.fragments.nutrition.mvi.effect.NutritionEffect
import com.example.main.fragments.nutrition.mvi.intent.NutritionIntent
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import com.example.main.fragments.nutrition.mvi.store.NutritionStore
import com.example.main.fragments.nutrition.recyclerview.adapter.MealsListAdapter
import com.example.main.fragments.nutrition.recyclerview.decoration.MealItemDecoration
import com.example.main.views.initialize
import com.example.main.views.setValue
import com.example.nutrition.entity.MealEntity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NutritionFragment :
    BaseFragmentMvi<NutritionPartialState, NutritionIntent, NutritionState, NutritionEffect>(R.layout.fragment_nutrition) {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    override val store: NutritionStore by viewModels()

    private var dateParam = ""

    private val mealsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MealsListAdapter { mealDateTimeOfCreation ->
            store.postIntent(NutritionIntent.DeleteMealByDate(dateParam, mealDateTimeOfCreation))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerForFragmentResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateParam = NutritionFragmentArgs.fromBundle(requireArguments()).date

        binding.run {
            cardNutrition.initialize()
            buttonAddMeal.setOnClickListener {
                store.postIntent(NutritionIntent.OpenAddMealDialog)
            }
        }
        mealsRecyclerViewSetup()

        store.postIntent(NutritionIntent.DateReceivedFromArgs(dateParam))
        store.postIntent(NutritionIntent.GetNutritionWithMealsByDate(dateParam))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(state: NutritionState) {
        binding.run {
            textViewDateNutrition.text = state.dateUi
            state.nutritionWithMealsUi.let {
                cardNutrition.setValue(it.totalNutrition)
                mealsAdapter.submitList(it.mealsList)
            }
        }
    }

    override fun resolveEffect(effect: NutritionEffect) {
        when (effect) {
            NutritionEffect.ShowAddMealDialog -> showAddMealDialog()
        }
    }

    private fun registerForFragmentResult() {
        childFragmentManager.setFragmentResultListener(
            AddMealDialog.REQUEST_KEY_ENTERED_MEAL_TO_ADD,
            this
        ) { _, bundle ->
            val mealJsonString = bundle.getString(AddMealDialog.BUNDLE_KEY_MEAL)
            val gson = Gson()
            val mealEntityToAdd = gson.fromJson(mealJsonString, MealEntity::class.java)

            store.postIntent(NutritionIntent.AddMealByDate(dateParam, mealEntityToAdd))
        }
    }

    private fun showAddMealDialog() {
        AddMealDialog().show(childFragmentManager, null)
    }

    private fun mealsRecyclerViewSetup() {
        binding.recyclerViewMeals.adapter = mealsAdapter
        binding.recyclerViewMeals.addItemDecoration(
            MealItemDecoration(
                resources.getDimension(R.dimen._16dp).toInt(),
                resources.getDimension(R.dimen._16dp).toInt(),
                0
            )
        )
    }
}