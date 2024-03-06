package com.chscorp.keepyourthings.domain.useCase.validatePassword

import com.chscorp.keepyourthings.domain.useCase.ValidationResult

interface ValidatePasswordUseCase {
    fun execute(password:String): ValidationResult
}