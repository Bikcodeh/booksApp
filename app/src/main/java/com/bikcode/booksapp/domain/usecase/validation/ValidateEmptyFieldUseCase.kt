package com.bikcode.booksapp.domain.usecase.validation

import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.commons.BaseValidationUseCase
import com.bikcode.booksapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmptyFieldUseCase @Inject constructor() :
    BaseValidationUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.name_required)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}