package com.example.main.fragments.activity_by_day.mvi.effect

import com.example.common.mvi.MviEffect

sealed interface ActivityByDayEffect : MviEffect{
    data object OpenSelectDateDialogEffect: ActivityByDayEffect
}