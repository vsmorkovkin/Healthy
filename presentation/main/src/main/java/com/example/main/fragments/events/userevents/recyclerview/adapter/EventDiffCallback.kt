package com.example.main.fragments.events.userevents.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.events.entity.EventEntity

class EventDiffCallback : DiffUtil.ItemCallback<EventEntity>() {
    override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
        return oldItem == newItem
    }
}
