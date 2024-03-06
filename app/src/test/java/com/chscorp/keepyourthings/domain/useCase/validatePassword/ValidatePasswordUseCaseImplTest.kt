package com.chscorp.keepyourthings.domain.useCase.validatePassword

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseImplTest {
    private lateinit var validatePassword: ValidatePasswordUseCaseImpl

    @Before
    fun setUp() {
        validatePassword = ValidatePasswordUseCaseImpl()
    }

    @Test
    fun  `Password is letter-only, returns error`() {
        val result = validatePassword.execute("abcdefgh")

        assertEquals(result.successful, false)
    }

    @Test
    fun  `Password is number-only, returns error`() {
        val result = validatePassword.execute("12345678")

        assertEquals(result.successful, false)
    }

    @Test
    fun  `Password is letter and number, returns success`() {
        val result = validatePassword.execute("a2cd3fg6")

        assertEquals(result.successful, true)
    }
}