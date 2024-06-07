package com.example.auth.fragments.login

import com.example.common.mvi.MviEffect

sealed interface LoginEffect : MviEffect {
    data object SuccessfulLogin : LoginEffect
    data class LoginFailure(val message: String) : LoginEffect
    data object RegisterEffect : LoginEffect
}