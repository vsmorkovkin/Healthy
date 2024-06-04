package com.example.main.fragments.nutrition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.common.mvi.BaseFragmentMvi
import com.example.main.R
import com.example.main.databinding.FragmentNutritionBinding
import com.example.main.fragments.activity_by_day.model.NutritionUi
import com.example.main.fragments.nutrition.dialog.AddMealDialog
import com.example.main.fragments.nutrition.model.MealUi
import com.example.main.fragments.nutrition.mvi.effect.NutritionEffect
import com.example.main.fragments.nutrition.mvi.intent.NutritionIntent
import com.example.main.fragments.nutrition.mvi.state.NutritionPartialState
import com.example.main.fragments.nutrition.mvi.state.NutritionState
import com.example.main.fragments.nutrition.mvi.store.NutritionStore
import com.example.main.fragments.nutrition.recycler_view.adapter.MealsListAdapter
import com.example.main.fragments.nutrition.recycler_view.decoration.MealItemDecoration
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

    private val mealsAdapter by lazy(LazyThreadSafetyMode.NONE) { MealsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener(AddMealDialog.REQUEST_KEY_ENTERED_MEAL_TO_ADD, this) { _, bundle ->

            val mealJsonString = bundle.getString(AddMealDialog.BUNDLE_KEY_MEAL)
            val gson = Gson()
            val mealEntityToAdd = gson.fromJson(mealJsonString, MealEntity::class.java)

            store.postIntent(NutritionIntent.AddMealByDate(mealEntityToAdd))

            Log.d("Nutrition", "meal: $mealEntityToAdd")
            Toast.makeText(requireContext(), "Meal added", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardNutrition.initialize()

        val date = NutritionFragmentArgs.fromBundle(requireArguments()).date
        store.postIntent(NutritionIntent.DateReceivedFromArgs(date))

        binding.buttonAddMeal.setOnClickListener {
            store.postIntent(NutritionIntent.OpenAddMealDialog)
        }

        mealsRecyclerViewSetup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(state: NutritionState) {
        binding.run {
            textViewDateNutrition.text = state.date
            state.nutritionUi?.let {
                cardNutrition.setValue(it)
            }
            mealsAdapter.submitList(state.mealsUi)
        }
    }

    override fun resolveEffect(effect: NutritionEffect) {
        when (effect) {
            NutritionEffect.ShowAddMealDialog -> showAddMealDialog()
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

        mealsAdapter.submitList(
            listOf(
                MealUi(1, "Завтрак", NutritionUi(210, 22, 45, 93)),
                MealUi(2, "Обед", NutritionUi(314, 32, 41, 89)),
                MealUi(3, "Ужин", NutritionUi(576, 25, 55, 115)),
                MealUi(4, "Завтрак", NutritionUi(210, 22, 45, 93)),
                MealUi(5, "Обед", NutritionUi(314, 32, 41, 89)),
                MealUi(6, "Ужин", NutritionUi(576, 25, 55, 115)),
            )
        )
    }

}