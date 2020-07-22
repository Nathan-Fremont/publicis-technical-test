package com.example.publicistechnicaltest.ui

import android.app.Application
import com.example.publicistechnicaltest.BuildConfig
import com.example.publicistechnicaltest.domain.di.koinDomainModules
import com.example.publicistechnicaltest.repository.di.koinRepositoryModules
import com.example.publicistechnicaltest.ui.di.koinUiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Do not delete, used in AndroidManifest
 */
class MyApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@MyApplication)
            modules(koinDomainModules)
            modules(koinRepositoryModules)
            modules(koinUiModules)
        }
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}