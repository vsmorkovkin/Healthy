package com.example.activity.repository

import com.example.activity.datasource.ActivityLocalDataSource
import com.example.activity.entity.ActivityEntity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityLocalDataSource: ActivityLocalDataSource
) : ActivityRepository {

    companion object {
        const val COLLECTION_USERS = "users"
        const val COLLECTION_ACTIVITIES = "activities"
    }

    override suspend fun getActivityByDate(date: String): ActivityEntity {
        val db = FirebaseFirestore.getInstance()
        val userId = Firebase.auth.currentUser?.uid ?: return ActivityEntity()

        val document = db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_ACTIVITIES)
            .document(date)
            .get()
            .await()

        if (document != null && document.exists()) {
            val activity = document.toObject<ActivityEntity>()
            if (activity != null) return activity
        }

        return ActivityEntity()
    }

    override suspend fun updateActivity(date: String, activityEntity: ActivityEntity) {
        val db = FirebaseFirestore.getInstance()
        val userId = Firebase.auth.currentUser?.uid ?: return

        db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_ACTIVITIES)
            .document(date)
            .set(activityEntity)
            .await()
    }

    override fun getInitialSteps(date: String): Int {
        return activityLocalDataSource.getInitialSteps(date)
    }

    override fun setInitialSteps(date: String, initialSteps: Int) {
        activityLocalDataSource.saveInitialSteps(date, initialSteps)
    }
}