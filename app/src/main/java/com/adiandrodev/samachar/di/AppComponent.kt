package com.adiandrodev.samachar.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    RemoteDataModule::class,
    CacheDataModule::class,
])
interface AppComponent {
    fun profileSubComponent(): MainSubComponent.Factory
}