package com.intuit.sendhil.gitexplorer.di

import com.intuit.sendhil.gitexplorer.ui.GitExplorerMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): GitExplorerMainActivity

}