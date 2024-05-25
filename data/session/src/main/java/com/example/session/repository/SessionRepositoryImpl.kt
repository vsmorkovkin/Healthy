package com.example.session.repository

import com.example.session.entity.UserProfileEntity
import com.example.session.entity.UserRegisterEntity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class SessionRepositoryImpl : SessionRepository {
    override suspend fun login(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }

    override suspend fun register(userRegisterEntity: UserRegisterEntity) {
        val email = userRegisterEntity.email
        val password = userRegisterEntity.password
        val firstName = userRegisterEntity.name
        val lastName = userRegisterEntity.surname

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    user?.let {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName("$firstName $lastName")
                            .build()

                        it.updateProfile(profileUpdates)
                    }
                }
            }
            .await()
    }

    override suspend fun getUserProfile(): UserProfileEntity {
        val currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val displayName = currentUser.displayName!!
        val nameParts = displayName.split(" ")
        val name = if (nameParts.isNotEmpty()) nameParts[0] else "N/A"
        val surname = if (nameParts.size > 1) nameParts[1] else "N/A"

        val photoUrl = currentUser.photoUrl?.toString()

        return UserProfileEntity(
            name = name,
            surname = surname,
            imageUrl = photoUrl
        )
    }
}