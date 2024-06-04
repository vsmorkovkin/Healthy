package com.example.nutrition.repository

import android.util.Log
import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity
import com.example.nutrition.entity.NutritionWithMealsEntity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor() : NutritionRepository {

    companion object {
        const val COLLECTION_USERS = "users"
        const val COLLECTION_MEALS = "meals"
    }

    override suspend fun addMealByDate(mealEntity: MealEntity, date: String) {
        val db: FirebaseFirestore = Firebase.firestore
        val userId = Firebase.auth.currentUser?.uid ?: return

        val mealDocRef = db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_MEALS)
            .document(date)

        val documentSnapshot = mealDocRef.get().await()

        if (documentSnapshot.exists()) {
            val nutritionWithMealsEntity =
                documentSnapshot.toObject(NutritionWithMealsEntity::class.java)
                    ?: NutritionWithMealsEntity()

            val totalNutrition = nutritionWithMealsEntity.totalNutrition
            val mealList = nutritionWithMealsEntity.mealsList.toMutableList()
            mealList.add(mealEntity)

            val updatedNutrition = updateTotalNutrition(totalNutrition, mealEntity.nutritionEntity)
            mealDocRef.set(NutritionWithMealsEntity(updatedNutrition, mealList)).await()
        } else {
            val newNutritionWithMeals =
                NutritionWithMealsEntity(mealEntity.nutritionEntity, listOf(mealEntity))
            mealDocRef.set(newNutritionWithMeals)
        }

        Log.d("Meal", "repository: meal added")
    }

    private fun updateTotalNutrition(
        existingNutrition: NutritionEntity,
        newNutrition: NutritionEntity
    ): NutritionEntity {
        return NutritionEntity(
            calories = existingNutrition.calories + newNutrition.calories,
            proteins = existingNutrition.proteins + newNutrition.proteins,
            fats = existingNutrition.fats + newNutrition.fats,
            carbs = existingNutrition.carbs + newNutrition.carbs
        )
    }

    override fun deleteMealByDate(date: String) {
        TODO("Not yet implemented")
    }

    override fun getNutritionByDate(date: String): NutritionEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getNutritionWithMeals(date: String): NutritionWithMealsEntity {
        val db: FirebaseFirestore = Firebase.firestore
        val userId = Firebase.auth.currentUser?.uid ?: throw Exception("No user")
        val mealDocRef = db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_MEALS)
            .document(date)

        val documentSnapshot = mealDocRef.get().await()

        return documentSnapshot.toObject(NutritionWithMealsEntity::class.java)
            ?: NutritionWithMealsEntity()
    }
}