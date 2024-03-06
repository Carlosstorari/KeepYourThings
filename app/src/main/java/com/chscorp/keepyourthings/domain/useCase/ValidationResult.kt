package com.chscorp.keepyourthings.domain.useCase

class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)