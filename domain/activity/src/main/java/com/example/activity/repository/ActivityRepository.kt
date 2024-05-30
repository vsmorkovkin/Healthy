package com.example.activity.repository

import com.example.activity.entity.ActivityEntity

interface ActivityRepository {
    suspend fun getActivityByDate(date: String): ActivityEntity?
    fun getLastLocalActivity(): ActivityEntity?
    fun saveActivityLocally(activityEntity: ActivityEntity)
    fun saveActivityRemotely(activityEntity: ActivityEntity)
}