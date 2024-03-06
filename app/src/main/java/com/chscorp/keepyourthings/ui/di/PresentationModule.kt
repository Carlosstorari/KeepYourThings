package com.chscorp.keepyourthings.ui.di

import com.chscorp.keepyourthings.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

    fun loadViewModelModule() {
        loadKoinModules(viewModelModule)
    }

    private val viewModelModule = module {
        viewModel { LoginViewModel(get(), get()) }
    }
}