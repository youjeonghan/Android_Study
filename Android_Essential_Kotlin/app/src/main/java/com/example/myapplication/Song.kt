package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val title: String? = null,
    val thumbnail: String? = null,
    val song: String? = null,
)