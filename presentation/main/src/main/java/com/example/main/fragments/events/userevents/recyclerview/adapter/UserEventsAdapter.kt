package com.example.main.fragments.events.userevents.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.events.entity.EventEntity
import com.example.main.databinding.CardUserEventBinding

class UserEventsAdapter :
    ListAdapter<EventEntity, UserEventsAdapter.UserEventsViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEventsViewHolder {
        val binding = CardUserEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserEventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserEventsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class UserEventsViewHolder(private val binding: CardUserEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventEntity) {
            binding.run {
                textViewEventTitle.text = item.title
                textViewDescription.text = item.dateTime.toString()
                textViewParticipantNumber.text = item.participantNumber.toString()
            }
        }
    }


}