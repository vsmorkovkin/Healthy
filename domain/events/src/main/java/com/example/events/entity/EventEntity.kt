package com.example.events.entity

data class EventEntity(
    val id: String,
    val title: String,
    val participantNumber: Int,
    val chatId: String,
    val dateTime: Long
)
