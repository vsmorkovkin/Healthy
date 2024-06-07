package com.example.auth.fragments.registration.mvi.intent

import com.example.auth.fragments.registration.model.UserRegistrationUi
import com.example.common.mvi.MviIntent

sealed interface RegistrationIntent : MviIntent {
    data class RegisterIntent(val userRegistrationUi: UserRegistrationUi) : RegistrationIntent
}