package com.example.main.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.session.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userProfileUrl: MutableSharedFlow<Uri?> = MutableSharedFlow()
    val userProfileUrl: SharedFlow<Uri?> get() = _userProfileUrl.asSharedFlow()

    fun getUserProfile() {
        viewModelScope.launch {
            runCatching {
                getUserProfileUseCase.invoke()
            }.onSuccess {
                it.imageUrl?.let { url ->
                    val userProfileImageUri = Uri.parse(url)
                    _userProfileUrl.emit(userProfileImageUri)
                }
            }
        }
    }

}