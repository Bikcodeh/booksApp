package com.bikcode.booksapp.domain.usecase.validation

import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.functions.isEmailValid
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.commons.BaseValidationUseCase
import com.bikcode.booksapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() : BaseValidationUseCase<String, ValidationResult> {
    override fun execute(input: String, errorString: Int?): ValidationResult {
        return when {
            input.isBlank() -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strTheEmailCanNotBeBlank)
            )

            !isEmailValid(input) -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThatsNotAValidEmail)
            )

            else -> ValidationResult(
                successful = true,
                errorMessage = null
            )
        }
    }
}