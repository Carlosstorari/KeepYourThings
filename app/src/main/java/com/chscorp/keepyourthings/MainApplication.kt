package com.chscorp.keepyourthings

import android.app.Application
import com.chscorp.keepyourthings.domain.di.DomainModule
import com.chscorp.keepyourthings.ui.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
        }
        DomainModule.loadDomainModules()
//        RepositoryModule.loadRepositoryModules()
        PresentationModule.loadViewModelModule()
    }
}