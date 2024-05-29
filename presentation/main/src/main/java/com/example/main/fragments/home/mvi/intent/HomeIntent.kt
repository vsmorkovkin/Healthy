package com.example.main.fragments.home.mvi.intent

import com.example.common.mvi.MviIntent

sealed interface HomeIntent : MviIntent {
    data object GetCurrentDate : HomeIntent
}