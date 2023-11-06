package com.bikcode.booksapp.domain.commons

import androidx.annotation.StringRes

interface BaseValidationUseCase<In, Out> {
    fun execute(input: In, @StringRes errorString: Int? = null): Out
}