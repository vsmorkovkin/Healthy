package com.example.main.fragments.nutrition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.main.R
import com.example.main.databinding.FragmentNutritionBinding
import com.example.main.fragments.nutrition.model.MealUi
import com.example.main.fragments.nutrition.recycler_view.adapter.MealsListAdapter
import com.example.main.fragments.nutrition.recycler_view.decoration.MealItemDecoration
import com.example.main.views.initialize


class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

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
        binding.textViewDateNutrition.text = date

        val adapter = MealsListAdapter()
        binding.recyclerViewMeals.adapter = adapter
        binding.recyclerViewMeals.addItemDecoration(MealItemDecoration(
            resources.getDimension(R.dimen._16dp).toInt(),
            resources.getDimension(R.dimen._16dp).toInt(),
            0
        ))
        adapter.submitList(
            listOf(
                MealUi("Завтрак", 210, 22, 45, 93),
                MealUi("Обед", 314, 32, 41, 89),
                MealUi("Ужин", 576, 25, 55, 115),
                MealUi("Завтрак", 210, 22, 45, 93),
                MealUi("Обед", 314, 32, 41, 89),
                MealUi("Ужин", 576, 25, 55, 115),
            )
        )

        Log.d("Meal", resources.getDimension(R.dimen._16dp).toString())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}