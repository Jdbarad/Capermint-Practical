package com.jdbarad.jaypalsinhbarad.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val page: Int = 0,
    val per_page: Int = 0,
    val total: Int = 0,
    val total_pages: Int = 0,
    val data: List<User> = listOf(),
    val support: Support = Support()
)

@Serializable
data class User(
    val id: Int = 0,
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val avatar: String = ""
)

@Serializable
data class Support(
    val url: String = "",
    val text: String = ""
)