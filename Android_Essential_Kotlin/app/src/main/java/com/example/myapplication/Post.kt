package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val owner: String? = null,
    var content: String? = null,
    var image: String? = null
)