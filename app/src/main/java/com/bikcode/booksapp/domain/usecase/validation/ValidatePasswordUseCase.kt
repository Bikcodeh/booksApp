package com.bikcode.booksapp.domain.usecase.validation

import com.bikcode.booksapp.R
import com.bikcode.booksapp.core.functions.isPasswordValid
import com.bikcode.booksapp.core.generic.UiText
import com.bikcode.booksapp.domain.commons.BaseValidationUseCase
import com.bikcode.booksapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(): BaseValidationUseCase<String, ValidationResult> {
    override fun execute(input: String, errorString: Int?): ValidationResult {
        if (input.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters),
            )
        }

        if (!isPasswordValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToContainAtLeastOneLetterAndDigit),
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}