package com.bikcode.booksapp.domain.commons

import com.bikcode.booksapp.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class Failure(val error: Exception?, val code: Int = 0): Exception() {
    class UnknownException(error: Exception? = null) : Failure(error)
    class NetworkConnection(error: Exception? = null) : Failure(error)
    class ServerError(code: Int) : Failure(null, code)
    class ParsingException(error: Exception? = null) : Failure(error)
    class HttpInternalException(code: Int) : Failure(null, code)

    companion object {
        fun analyzeException(exception: Exception?): Failure {
            return when (exception) {
                is UnknownHostException,
                is ConnectException,
                is TimeoutException -> NetworkConnection(exception)
                is NullPointerException -> ParsingException(exception)
                is HttpException -> HttpInternalException(exception.code())
                else -> UnknownException(exception)
            }
        }

        fun getMessageResId(failure: Failure): Int {
            return when (failure) {
                is NetworkConnection -> R.string.error_connection
                is ParsingException -> R.string.error_parsing
                is ServerError -> R.string.error_server
                is UnknownException -> R.string.error_unknown
                is HttpInternalException -> handleHttpError(failure.code)
            }
        }

        private fun handleHttpError(errorCode: Int): Int {
            return when (errorCode) {
                500 -> R.string.error_server
                503 -> R.string.error_server_limit_allowed
                504 -> R.string.error_timeout
                404 -> R.string.error_not_found_error
                400 -> R.string.error_invalid_request_error
                403 -> R.string.error_unauthorized_error
                else -> R.string.error_unknown
            }
        }
    }
}