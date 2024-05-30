package com.example.activity.repository

import com.example.activity.datasource.ActivityLocalDataSource
import com.example.activity.entity.ActivityEntity
import com.example.activity.model.ActivityFirebaseModel
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

    override suspend fun getActivityByDate(date: String): ActivityEntity? {
        val db = FirebaseFirestore.getInstance()

        val userId = Firebase.auth.currentUser?.uid ?: return null

        val document = db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_ACTIVITIES)
            .document(date)
            .get()
            .await()

        if (document != null && document.exists()) {
            val activityFirebaseModel = document.toObject<ActivityFirebaseModel>()
            return activityFirebaseModel?.toDomain()
        }
        return null
    }

    override fun getLastLocalActivity(): ActivityEntity? {
        return activityLocalDataSource.getLastActivity()
    }

    override fun saveActivityLocally(activityEntity: ActivityEntity) {
        activityLocalDataSource.saveActivity(activityEntity)
    }

    override fun saveActivityRemotely(activityEntity: ActivityEntity) {
        val db = FirebaseFirestore.getInstance()

        val userId = Firebase.auth.currentUser?.uid ?: return

        db.collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_ACTIVITIES)
            .document(activityEntity.day)
            .set(activityEntity)
    }

}