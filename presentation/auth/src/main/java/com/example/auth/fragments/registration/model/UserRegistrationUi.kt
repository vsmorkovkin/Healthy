package com.example.auth.fragments.registration.model

data class UserRegistrationUi(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
)