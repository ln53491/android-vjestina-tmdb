package com.example.tmdb

import android.app.Application
import com.example.tmdb.repository.apiModule
import com.example.tmdb.repository.repositoryModule
import com.example.tmdb.repository.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@TestApp)
            modules(listOf(viewModelModule))
            modules(repositoryModule)
            modules(apiModule)
        }
    }
}