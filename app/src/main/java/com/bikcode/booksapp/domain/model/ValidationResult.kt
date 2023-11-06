package com.bikcode.booksapp.domain.model

import com.bikcode.booksapp.core.generic.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)