package com.bangkit.capsstonebangkit.application

import android.app.Application
import com.bangkit.capsstonebangkit.di.networkModule
import com.bangkit.capsstonebangkit.di.repositoryModule
import com.bangkit.capsstonebangkit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }

}