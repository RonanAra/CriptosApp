package com.example.coinbase.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@MyApp)
            modules(mainModule)
        }
    }
}