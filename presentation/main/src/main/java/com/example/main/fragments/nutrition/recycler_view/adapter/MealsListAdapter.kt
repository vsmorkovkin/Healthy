package com.example.main.fragments.nutrition.recycler_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.CardMealBinding
import com.example.main.fragments.nutrition.model.MealUi
import com.example.main.views.initialize
import com.example.main.views.setMeal

class MealsListAdapter : ListAdapter<MealUi, MealsListAdapter.MealViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = CardMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.initialize()
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MealViewHolder(private val binding: CardMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealUi) {
            binding.setMeal(item)
        }
    }

}