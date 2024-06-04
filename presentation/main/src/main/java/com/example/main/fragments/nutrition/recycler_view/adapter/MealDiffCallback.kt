package com.example.main.fragments.nutrition.recycler_view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.main.fragments.nutrition.model.MealUi

class MealDiffCallback : DiffUtil.ItemCallback<MealUi>() {
    override fun areItemsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
        return oldItem.dateTimeOfCreation == newItem.dateTimeOfCreation
    }

    override fun areContentsTheSame(oldItem: MealUi, newItem: MealUi): Boolean {
        return oldItem == newItem
    }
}