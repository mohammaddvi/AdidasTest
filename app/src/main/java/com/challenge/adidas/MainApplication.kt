package com.challenge.adidas

import android.app.Application
import com.challenge.adidas.di.productModule
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(productModule)
        }
    }
}