package com.jdbarad.jaypalsinhbarad.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


val baseUrl = "reqres.in/api"

fun ktorClient() = HttpClient(Android) {

    defaultRequest {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        url {
            protocol = URLProtocol.HTTPS
            host = baseUrl
        }
    }

    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = true
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("LoggerAPI", message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(WebSockets) {
        pingInterval = 20000
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }

}