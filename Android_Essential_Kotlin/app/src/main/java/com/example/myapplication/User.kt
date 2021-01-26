package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var username: String? = null,
    var token: String? = null
)