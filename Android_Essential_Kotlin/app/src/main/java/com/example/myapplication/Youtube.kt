package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class Youtube (
    var title: String? = null,
    var content: String? = null,
    var thumbnail: String? = null,
    var video: String? = null,
)