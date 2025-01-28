package com.jdbarad.jaypalsinhbarad.data.remote

import com.jdbarad.jaypalsinhbarad.data.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class Repository(private val httpClient: HttpClient) {

    suspend fun getUsers() = safeApiCall<UserResponse> {
        httpClient.get("users")
    }

}