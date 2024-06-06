package com.example.activity.repository

import com.example.activity.entity.ActivityEntity
import com.example.activity.entity.ActivityWithNutritionEntity

interface ActivityRepository {
    suspend fun getActivityByDate(date: String): ActivityEntity
    suspend fun updateActivity(date: String, activityEntity: ActivityEntity)
}