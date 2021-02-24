package com.dev.clevertonsantos.nybooks

import android.app.Application
import com.dev.clevertonsantos.nybooks.di.booksModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)

            modules(booksModule)
        }
    }
}