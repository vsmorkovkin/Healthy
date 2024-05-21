package com.example.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session.SessionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionService: SessionService
) : ViewModel() {

    fun login(email: String, password: String, action: () -> Unit) {
        runCatching {
            viewModelScope.launch {
                sessionService.signIn(
                    email, password
                )
            }
        }.fold(
            onSuccess = {
                action()
            },
            onFailure = {}
        )

    }

}