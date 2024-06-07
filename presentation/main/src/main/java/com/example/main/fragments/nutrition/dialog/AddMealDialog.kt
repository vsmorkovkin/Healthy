package com.example.main.fragments.nutrition.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.main.R
import com.example.main.databinding.DialogAddMealBinding
import com.example.main.extensions.getText
import com.example.main.extensions.showToast
import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity
import com.google.gson.Gson
import java.util.Calendar

class AddMealDialog : DialogFragment() {

    private var _binding: DialogAddMealBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddMealNutritionDialog.setOnClickListener { onClickButtonAdd() }
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.run {
            setBackgroundDrawableResource(R.drawable.bg_dialog)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickButtonAdd() {
        binding.run {
            val title = textInputLayoutMealTitle.getText()
            val caloriesText = textInputLayoutMealCalories.getText()
            val proteinsText = textInputLayoutMealProteins.getText()
            val fatsText = textInputLayoutMealFats.getText()
            val carbsText = textInputLayoutMealCarbs.getText()

            val conditionWithMessageList = listOf(
                title.isEmpty() to getString(R.string.warning_empty_meal_title),
                caloriesText.isEmpty() to getString(R.string.warning_empty_calories),
                proteinsText.isEmpty() to getString(R.string.warning_empty_proteins),
                fatsText.isEmpty() to getString(R.string.warning_empty_fats),
                carbsText.isEmpty() to getString(R.string.warning_empty_carbs)
            )

            for (conditionWithMessage in conditionWithMessageList) {
                if (conditionWithMessage.first) {
                    showToast(conditionWithMessage.second)
                    return
                }
            }

            val gson = Gson()

            val mealEntity = MealEntity(
                Calendar.getInstance().timeInMillis,
                title,
                NutritionEntity(
                    caloriesText.toInt(),
                    proteinsText.toInt(),
                    fatsText.toInt(),
                    carbsText.toInt()
                )
            )
            val mealJsonString = gson.toJson(mealEntity)

            parentFragmentManager.setFragmentResult(REQUEST_KEY_ENTERED_MEAL_TO_ADD, bundleOf(
                BUNDLE_KEY_MEAL to mealJsonString
            ))
            dismiss()
        }
    }

    companion object {
        const val REQUEST_KEY_ENTERED_MEAL_TO_ADD = "REQUEST_KEY_ENTERED_MEAL_TO_ADD"
        const val BUNDLE_KEY_MEAL = "BUNDLE_KEY_MEAL"
    }
}