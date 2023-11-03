package com.bikcode.booksapp.domain.commons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

/**
 * Allows to make a request in a safe way catching possible errors
 * and sending report to app center and prints log with timber
 * @return Result<T>: returns a Result wrapper with the given expected data
 */
suspend fun <T : Any> makeSafeRequest(
    execute: suspend () -> Response<T>
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                Result.Error(Failure.HttpInternalException(response.code()))
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.Error(Failure.analyzeException(e))
        }
    }
}