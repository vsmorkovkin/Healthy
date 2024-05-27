package com.example.common.navigation

interface DeeplinkHandler {
    fun process(deeplink: String): Boolean
}