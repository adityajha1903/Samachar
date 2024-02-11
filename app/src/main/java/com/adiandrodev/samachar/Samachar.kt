package com.adiandrodev.samachar

import android.app.Application
import com.adiandrodev.samachar.di.AppComponent
import com.adiandrodev.samachar.di.DaggerAppComponent
import com.adiandrodev.samachar.di.MainSubComponent
import com.adiandrodev.samachar.di.RemoteDataModule

interface Injector {
    fun createMainSubComponent(): MainSubComponent
}

class Samachar: Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
        super.onCreate()
    }

    override fun createMainSubComponent(): MainSubComponent {
        return appComponent.profileSubComponent().create()
    }
}