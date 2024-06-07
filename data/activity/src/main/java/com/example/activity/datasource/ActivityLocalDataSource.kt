package com.example.activity.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ActivityLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val ACTIVITY_SHARED_PREFS = "ACTIVITY_SHARED_PREFS"
        const val KEY_LAST_ACTIVITY = "KEY_INITIAL_STEPS"
        const val KEY_SAVE_DATE_INITIAL_STEPS = "KEY_SAVE_DATE_INITIAL_STEPS"
        const val NO_INITIALS_STEPS_VALUE = -1
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(ACTIVITY_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    fun getInitialSteps(date: String): Int {
        val saveDateOfInitialSteps = sharedPreferences.getString(KEY_SAVE_DATE_INITIAL_STEPS, null)

        if (saveDateOfInitialSteps == null || saveDateOfInitialSteps != date) {
            return NO_INITIALS_STEPS_VALUE
        }

        return sharedPreferences.getInt(KEY_LAST_ACTIVITY, NO_INITIALS_STEPS_VALUE)
    }

    fun saveInitialSteps(date: String, initialSteps: Int) {
        sharedPreferences.edit()
            .putString(KEY_SAVE_DATE_INITIAL_STEPS, date)
            .putInt(KEY_LAST_ACTIVITY, initialSteps)
            .apply()
    }
}