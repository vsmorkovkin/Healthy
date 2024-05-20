package com.example.session

import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow

interface SessionService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}