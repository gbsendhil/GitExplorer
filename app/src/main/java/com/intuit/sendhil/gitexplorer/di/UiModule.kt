package com.intuit.sendhil.gitexplorer.di

import com.intuit.sendhil.gitexplorer.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
    
}