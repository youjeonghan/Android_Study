package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Register (
    var username: String? = null,
    var password1: String? = null,
    var password2: String? = null
)