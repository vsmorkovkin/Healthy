package com.example.activity.datasource

import android.content.Context
import com.example.activity.entity.ActivityEntity
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ActivityLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val ACTIVITY_SHARED_PREFS = "ACTIVITY_SHARED_PREFS"
        const val LAST_ACTIVITY_KEY = "LAST_ACTIVITY_KEY"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(ACTIVITY_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    fun getLastActivity(): ActivityEntity? {
        val gson = Gson()
        val activityEntityJson = sharedPreferences.getString(LAST_ACTIVITY_KEY, null)

        return activityEntityJson?.let {
            gson.fromJson(it, ActivityEntity::class.java)
        }
    }

    fun saveActivity(activityEntity: ActivityEntity) {
        val gson = Gson()
        val activityEntityJson = gson.toJson(activityEntity)
        sharedPreferences.edit()
            .putString(LAST_ACTIVITY_KEY, activityEntityJson)
            .apply()
    }

}