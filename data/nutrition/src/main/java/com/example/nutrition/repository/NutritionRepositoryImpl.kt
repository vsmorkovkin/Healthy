package com.example.nutrition.repository

import com.example.nutrition.entity.MealEntity
import com.example.nutrition.entity.NutritionEntity
import com.example.nutrition.entity.NutritionWithMealsEntity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor() : NutritionRepository {

    companion object {
        const val COLLECTION_USERS = "users"
        const val COLLECTION_MEALS = "meals"
    }

    private fun getMealDocRef(date: String): DocumentReference? {
        val db: FirebaseFirestore = Firebase.firestore
        val userId = Firebase.auth.currentUser?.uid ?: return null

        return db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_MEALS)
            .document(date)
    }

    override suspend fun addMealByDate(mealEntity: MealEntity, date: String) {
        val mealDocRef = getMealDocRef(date) ?: return

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

    override suspend fun deleteMealByDate(date: String, mealDateTimeOfCreation: Long) {
        val mealDocRef = getMealDocRef(date) ?: return

        val documentSnapshot = mealDocRef.get().await()

        if (documentSnapshot.exists()) {
            val nutritionWithMeals = documentSnapshot.toObject(NutritionWithMealsEntity::class.java)
            if (nutritionWithMeals != null) {
                // find meal by datetimeOfCreation
                val indexToRemove = nutritionWithMeals.mealsList.indexOfFirst { it.datetimeOfCreation == mealDateTimeOfCreation }
                if (indexToRemove == -1) return

                // subtract meal nutrition from total nutrition
                val mealToDelete = nutritionWithMeals.mealsList[indexToRemove]
                val updatedTotalNutrition = subtractTotalNutrition(nutritionWithMeals.totalNutrition, mealToDelete.nutritionEntity)

                // delete meal
                val updatedList = nutritionWithMeals.mealsList.toMutableList()
                updatedList.removeAt(indexToRemove)
                val updatedNutritionWithMeals = NutritionWithMealsEntity(updatedTotalNutrition, updatedList)

                // set updated nutrition with meals
                mealDocRef.set(updatedNutritionWithMeals)
            }
        }
    }

    private fun subtractTotalNutrition(existingNutrition: NutritionEntity, mealNutrition: NutritionEntity): NutritionEntity {
        return NutritionEntity(
            calories = existingNutrition.calories - mealNutrition.calories,
            proteins = existingNutrition.proteins - mealNutrition.proteins,
            fats = existingNutrition.fats - mealNutrition.fats,
            carbs = existingNutrition.carbs - mealNutrition.carbs
        )
    }

    override fun getNutritionByDate(date: String): NutritionEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getNutritionWithMeals(date: String): NutritionWithMealsEntity {
        val mealDocRef = getMealDocRef(date) ?: return NutritionWithMealsEntity()
        val documentSnapshot = mealDocRef.get().await()

        return documentSnapshot.toObject(NutritionWithMealsEntity::class.java)
            ?: NutritionWithMealsEntity()
    }
}