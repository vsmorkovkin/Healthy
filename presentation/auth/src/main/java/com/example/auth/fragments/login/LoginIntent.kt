package com.example.auth.fragments.login

import com.example.common.mvi.MviIntent

sealed interface LoginIntent : MviIntent {
    data class SignInIntent(
        val email: String,
        val password: String
    ) : LoginIntent
    data object NoAccountIntent : LoginIntent
}