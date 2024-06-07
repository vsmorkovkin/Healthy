package com.example.main.fragments.profile.model

import android.net.Uri
import com.example.session.entity.UserProfileEntity

fun UserProfileEntity.toUi(): UserProfileUi {
    val nameAndSurname = "${this.name} ${this.surname}"
    val imageUrl = this.imageUrl?.let {
        try {
            Uri.parse(this.imageUrl)
        } catch (_: Exception) {
            null
        }
    }
    return UserProfileUi(nameAndSurname, imageUrl)
}