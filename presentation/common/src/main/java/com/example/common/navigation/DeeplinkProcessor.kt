package com.example.common.navigation

interface DeeplinkProcessor {
    fun matches(deeplink: String): Boolean
    fun execute(deeplink: String)
}