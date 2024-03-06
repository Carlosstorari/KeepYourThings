package com.chscorp.keepyourthings.domain.di

import com.chscorp.keepyourthings.domain.useCase.validateEmail.ValidateEmailUseCase
import com.chscorp.keepyourthings.domain.useCase.validateEmail.ValidateEmailUseCaseImpl
import com.chscorp.keepyourthings.domain.useCase.validatePassword.ValidatePasswordUseCase
import com.chscorp.keepyourthings.domain.useCase.validatePassword.ValidatePasswordUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    fun loadDomainModules() {
        loadKoinModules(useCaseModule)
    }

    private val useCaseModule = module {
        single<ValidateEmailUseCase> { ValidateEmailUseCaseImpl() }
        single<ValidatePasswordUseCase> { ValidatePasswordUseCaseImpl() }
    }
}