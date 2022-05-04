package com.example.tmdb

import android.app.Application
import com.example.tmdb.ui.theme.repositoryModule
import com.example.tmdb.ui.theme.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}