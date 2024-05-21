package com.example.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session.SessionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionService: SessionService
) : ViewModel() {

    private val _hasUser = MutableStateFlow(false)
    val hasUser: StateFlow<Boolean>
        get() = _hasUser.asStateFlow()


    fun login(email: String, password: String) {
        viewModelScope.launch {
            sessionService.signIn(
                email, password
            )

            if (sessionService.hasUser()) {
                _hasUser.value = true
            }
        }
    }

}