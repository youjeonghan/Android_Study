package com.example.myapplication

import kotlinx.serialization.Serializable


@Serializable
data class PersonFromServer(
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null,
    var intro: String? = null
)