package com.example.main.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userProfileUrl: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val userProfileUrl: StateFlow<Uri?> get() = _userProfileUrl.asStateFlow()

    fun getUserProfile() {
        viewModelScope.launch {
            runCatching {
                getUserProfileUseCase.invoke()
            }.onSuccess {
                val userProfileImageUri = Uri.parse(it.imageUrl)
                _userProfileUrl.value = userProfileImageUri
            }
        }
    }

}