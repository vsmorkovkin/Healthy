package com.example.auth.fragments.registration.mvi.store

import com.example.auth.fragments.registration.model.UserRegistrationUi
import com.example.auth.fragments.registration.model.toDomain
import com.example.auth.fragments.registration.mvi.effect.RegistrationEffect
import com.example.auth.fragments.registration.mvi.intent.RegistrationIntent
import com.example.auth.fragments.registration.mvi.state.RegistrationPartialState
import com.example.auth.fragments.registration.mvi.state.RegistrationState
import com.example.common.mvi.MviActor
import com.example.session.usecase.RegisterUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegistrationActor @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : MviActor<RegistrationPartialState, RegistrationIntent, RegistrationState, RegistrationEffect>() {

    override fun resolve(
        intent: RegistrationIntent,
        state: RegistrationState
    ): Flow<RegistrationPartialState> {
        return when (intent) {
            is RegistrationIntent.RegisterIntent -> register(intent.userRegistrationUi)
        }
    }

    private fun register(userRegistrationUi: UserRegistrationUi): Flow<RegistrationPartialState> = flow {
        userRegistrationUi.run {
            val conditionToEffect = listOf(
                name.isEmpty() to RegistrationEffect.EmptyName,
                surname.isEmpty() to RegistrationEffect.EmptySurname,
                email.isEmpty() to RegistrationEffect.EmptyEmail,
                password.isEmpty() to RegistrationEffect.EmptyPassword,
                passwordConfirmation.isEmpty() to RegistrationEffect.EmptyPasswordConfirmation,
                (password != passwordConfirmation) to RegistrationEffect.NotMatchingPasswords,
            )

            for ((condition, effect) in conditionToEffect) {
                if (condition) {
                    _effects.emit(effect)
                    return@flow
                }
            }
        }

        runCatching {
            val userRegisterEntity = userRegistrationUi.toDomain()
            registerUseCase(userRegisterEntity)
        }.fold(
            onSuccess = { _effects.emit(RegistrationEffect.SuccessfulRegistration) },
            onFailure = { _effects.emit(RegistrationEffect.RegistrationFailure(it.message.orEmpty())) }
        )
    }
}