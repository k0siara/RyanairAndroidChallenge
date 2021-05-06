package com.patrykkosieradzki.ryanairandroidchallenge

import android.app.Application
import com.google.firebase.FirebaseApp
import com.patrykkosieradzki.ryanairandroidchallenge.di.appModule
import com.patrykkosieradzki.ryanairandroidchallenge.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RyanairApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RyanairApplication)
            modules(networkModule, appModule)
        }
    }
}
