package com.example.auth.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session.SessionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val sessionService: SessionService
) : ViewModel() {

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered get() = _isRegistered.asStateFlow()

    fun register(email: String, password: String) {
        runCatching {
            viewModelScope.launch {
                sessionService.signUp(email, password)
            }
        }.fold(
            onSuccess = {
                _isRegistered.value = true
            },
            onFailure = {}
        )

    }


}