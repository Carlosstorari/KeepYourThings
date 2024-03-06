package com.chscorp.keepyourthings.domain.useCase.validateEmail

import com.chscorp.keepyourthings.domain.useCase.ValidationResult

interface ValidateEmailUseCase {
    fun execute(email:String): ValidationResult
}