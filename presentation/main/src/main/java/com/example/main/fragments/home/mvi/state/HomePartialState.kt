package com.example.main.fragments.home.mvi.state

import com.example.common.mvi.MviPartialState

sealed interface HomePartialState : MviPartialState {
    data class CurrentDateLoaded(val currentDate: String) : HomePartialState
}