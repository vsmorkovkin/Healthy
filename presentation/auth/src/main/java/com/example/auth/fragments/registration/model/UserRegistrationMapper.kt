package com.example.auth.fragments.registration.model

import com.example.session.entity.UserRegisterEntity

fun UserRegistrationUi.toDomain(): UserRegisterEntity {
    return this.run {
        UserRegisterEntity(name, surname, email, password)
    }
}