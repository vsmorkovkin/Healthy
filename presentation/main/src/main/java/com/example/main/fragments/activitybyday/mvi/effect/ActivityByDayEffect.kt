package com.example.main.fragments.activitybyday.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface ActivityByDayEffect : MviEffect{
    data object OpenSelectDateDialogEffect: ActivityByDayEffect
}