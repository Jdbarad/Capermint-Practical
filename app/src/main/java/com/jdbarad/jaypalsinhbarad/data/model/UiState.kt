package com.jdbarad.jaypalsinhbarad.data.model

import androidx.compose.runtime.Composable

sealed class UiState<out T> {

    data object Idle : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val type: ErrorType) : UiState<T>()
    data object Loading : UiState<Nothing>()

    sealed class ErrorType(val message: String) {

        data class NETWORK(private val msg: String? = null) :
            ErrorType(msg.orEmpty().ifEmpty { "Network Error" })

        data class UNAUTHENTICATED(private val msg: String? = null) :
            ErrorType(msg.orEmpty().ifEmpty { "Unauthenticated" })

        data class SERVER(private val msg: String? = null) :
            ErrorType(msg.orEmpty().ifEmpty { "Internal Server Error" })

        data class DATA_NOT_FOUND(private val msg: String? = null) :
            ErrorType(msg.orEmpty().ifEmpty { "Data Not Found" })

        data class UNKNOWN(private val msg: String? = null) :
            ErrorType(msg.orEmpty().ifEmpty { "Unknown Error" })
    }

    @Composable
    fun onSuccess(success: @Composable (T) -> Unit): UiState<T> {
        if (this is Success) {
            success(data)
        }
        return this
    }

    @Composable
    fun onError(error: @Composable (ErrorType) -> Unit): UiState<T> {
        if (this is Error) {
            error(type)
        }
        return this
    }

    inline fun isSuccess(data: (T) -> Unit): UiState<T> {
        if (this is Success) {
            data(this.data)
        }
        return this
    }

    inline fun isError(data: (ErrorType) -> Unit): UiState<T> {
        if (this is Error) {
            data(this.type)
        }
        return this
    }

    @Composable
    fun setUi(
        loading: @Composable () -> Unit,
        error: @Composable (ErrorType) -> Unit,
        idle: @Composable () -> Unit,
        success: @Composable (T) -> Unit
    ) {
        when (this) {
            is Error -> error(type)
            is Success -> success(data)
            is Idle -> idle()
            is Loading -> loading()
        }
    }

    @Composable
    fun setUi(
        idleOrLoading: @Composable () -> Unit,
        success: @Composable (T) -> Unit,
        error: @Composable (ErrorType) -> Unit = {}
    ) {
        when (this) {
            is Success -> success(data)
            is Error -> error(type)
            else -> idleOrLoading()
        }
    }

    fun isSuccess(): T? {
        return if (this is Success) data else null
    }

    companion object {
        fun <T> success(data: T): UiState<T> {
            return Success(data)
        }

        fun <T> empty(message: String? = null): UiState<T> {
            return Error(ErrorType.DATA_NOT_FOUND(message))
        }

        fun <T> error(message: String? = null): UiState<T> {
            return Error(ErrorType.UNKNOWN(message))
        }

        fun <T> unAuthentication(message: String? = null): UiState<T> {
            return Error(ErrorType.UNAUTHENTICATED(message))
        }

        fun <T> serverError(message: String? = null): UiState<T> {
            return Error(ErrorType.SERVER(message))
        }

        fun <T> networkError(message: String? = null): UiState<T> {
            return Error(ErrorType.NETWORK(message))
        }
    }

}