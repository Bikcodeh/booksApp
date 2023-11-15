package com.bikcode.booksapp.domain.usecase.validation

import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.functions.isPasswordValid
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.commons.BaseValidationUseCase
import com.bikcode.booksapp.domain.model.ValidationResult

class ValidatePasswordUseCase() :
    BaseValidationUseCase<String, ValidationResult> {
    override fun execute(input: String, errorString: Int?): ValidationResult {
        return when {
            input.length < 8 -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters),
            )

            !isPasswordValid(input) -> ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToContainAtLeastOneLetterAndDigit),
            )

            else -> ValidationResult(
                successful = true
            )
        }
    }
}