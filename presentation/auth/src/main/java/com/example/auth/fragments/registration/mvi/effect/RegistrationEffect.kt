package com.example.auth.fragments.registration.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface RegistrationEffect : MviEffect {
    data object EmptyName : RegistrationEffect
    data object EmptySurname : RegistrationEffect
    data object EmptyEmail : RegistrationEffect
    data object EmptyPassword : RegistrationEffect
    data object EmptyPasswordConfirmation : RegistrationEffect
    data object NotMatchingPasswords : RegistrationEffect
    data object SuccessfulRegistration : RegistrationEffect
    data class RegistrationFailure(val message: String) : RegistrationEffect
}