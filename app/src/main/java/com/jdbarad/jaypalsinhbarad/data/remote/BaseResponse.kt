package com.jdbarad.jaypalsinhbarad.data.remote

import androidx.annotation.Keep
import com.jdbarad.jaypalsinhbarad.data.model.UiState
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable
import java.nio.channels.UnresolvedAddressException

@Keep
@Serializable
data class BaseResponse<T>(
    val success: Boolean = false,
    val message: String = "",
    val data: T? = null,
    val status: Status = Status.LOADING
) {
    companion object {
        fun <T> success(baseResponse: BaseResponse<T>): BaseResponse<T> {
            return baseResponse.copy(status = Status.SUCCESS)
        }

        fun <T> notFount(baseResponse: BaseResponse<T>): BaseResponse<T> {
            return baseResponse.copy(status = Status.NOT_FOUNT)
        }

        fun <T> error(message: String?): BaseResponse<T> {
            return BaseResponse(false, message ?: "", status = Status.ERROR)
        }

        fun <T> error(baseResponse: BaseResponse<T>): BaseResponse<T> {
            return baseResponse.copy(status = Status.ERROR)
        }

        fun <T> unAuthentication(message: String?): BaseResponse<T> {
            return BaseResponse(false, message ?: "", status = Status.UNAUTHENTICATED)
        }

        fun <T> loading(message: String?): BaseResponse<T> {
            return BaseResponse(false, message ?: "Loading...", status = Status.LOADING)
        }
    }

    enum class Status {
        SUCCESS,
        NOT_FOUNT,
        LOADING,
        ERROR,
        UNAUTHENTICATED
    }
}


suspend inline fun <reified T> safeApiCall(function: () -> HttpResponse): UiState<T> {
    return try {
        val response = function.invoke()
        when (response.status) {
            HttpStatusCode.OK -> UiState.success(response.body<T>())
            HttpStatusCode.NoContent -> UiState.empty()
            HttpStatusCode.Unauthorized -> UiState.unAuthentication(response.status.description)
            HttpStatusCode.InternalServerError -> UiState.serverError(response.status.description)
            else -> UiState.error(response.status.description)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        if (e is UnresolvedAddressException || e is ConnectTimeoutException) {
            return UiState.networkError(e.message)
        }
        UiState.error(e.message)
    }
}