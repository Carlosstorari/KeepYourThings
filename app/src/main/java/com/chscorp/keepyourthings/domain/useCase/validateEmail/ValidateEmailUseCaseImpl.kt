package com.chscorp.keepyourthings.domain.useCase.validateEmail

import android.util.Patterns
import com.chscorp.keepyourthings.domain.useCase.ValidationResult

class ValidateEmailUseCaseImpl : ValidateEmailUseCase {

    override fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"//todo passar pra resource
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}