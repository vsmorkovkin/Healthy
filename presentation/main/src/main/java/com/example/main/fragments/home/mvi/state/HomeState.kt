package com.example.main.fragments.home.mvi.state

import com.example.common.mvi.MviState

data class HomeState(
    val currentDate: String
) : MviState
