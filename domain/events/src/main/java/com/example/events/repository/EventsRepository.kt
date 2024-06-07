package com.example.events.repository

import com.example.events.entity.EventEntity

interface EventsRepository {
    fun getUserEvents(): List<EventEntity>
}