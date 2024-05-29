package com.example.session.repository

import android.net.Uri
import com.example.session.entity.UserProfileEntity
import com.example.session.entity.UserRegisterEntity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor() : SessionRepository {

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

    override suspend fun setUserProfileImage(imageUri: String): String? {
        val storageReference = FirebaseStorage.getInstance().reference
        val user = FirebaseAuth.getInstance().currentUser

        val profileImageRef = storageReference.child("profileImages/${user?.uid}.png")
        val localImageUri = Uri.parse(imageUri)

        profileImageRef.putFile(localImageUri).await() // save image in remote storage
        val downloadUrl = profileImageRef.downloadUrl.await() // get url for image in remote storage
        updateProfileWithImage(downloadUrl).await() // set url for image in remote storage to user photo url

        return downloadUrl?.toString()
    }

    private suspend fun updateProfileWithImage(downloadUrl: Uri) = coroutineScope {
        async {
            val user = FirebaseAuth.getInstance().currentUser
            user?.let { firebaseUser ->
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setPhotoUri(downloadUrl)
                    .build()

                firebaseUser.updateProfile(profileUpdates)
            }
        }
    }

}