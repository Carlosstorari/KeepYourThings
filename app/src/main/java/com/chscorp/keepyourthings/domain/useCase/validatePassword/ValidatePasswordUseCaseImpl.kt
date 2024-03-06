package com.chscorp.keepyourthings.domain.useCase.validatePassword

import android.util.Patterns
import com.chscorp.keepyourthings.domain.useCase.ValidationResult

class ValidatePasswordUseCaseImpl : ValidatePasswordUseCase {
    override fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password need to consist of at least 8 characters"//todo passar pra resource
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one digit and letter"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}