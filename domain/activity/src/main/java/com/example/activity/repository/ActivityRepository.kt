package com.example.activity.repository

import com.example.activity.entity.ActivityEntity

interface ActivityRepository {
    suspend fun getActivityByDate(date: String): ActivityEntity
    suspend fun updateActivity(date: String, activityEntity: ActivityEntity)
    fun getInitialSteps(date: String): Int
    fun setInitialSteps(date: String, initialSteps: Int)
}