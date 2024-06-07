package com.example.events.usecase

import com.example.events.entity.EventEntity
import com.example.events.repository.EventsRepository

class GetUserEventsUseCase(
    private val eventsRepository: EventsRepository
) {
    operator fun invoke(): List<EventEntity> {
        return eventsRepository.getUserEvents()
    }
}