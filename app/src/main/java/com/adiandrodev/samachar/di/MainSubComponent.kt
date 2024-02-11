package com.adiandrodev.samachar.di

import com.adiandrodev.samachar.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainSubComponent
    }
}