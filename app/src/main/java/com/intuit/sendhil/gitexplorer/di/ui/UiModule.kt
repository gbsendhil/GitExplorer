package com.intuit.sendhil.gitexplorer.di.ui

import com.intuit.sendhil.gitexplorer.di.list.GitRepoListFeatureModule
import com.intuit.sendhil.gitexplorer.ui.GitExplorerMainActivity
import com.intuit.sendhil.gitexplorer.ui.list.GitRepoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): GitExplorerMainActivity

    @ContributesAndroidInjector(modules = [GitRepoListFeatureModule::class])
    abstract fun bindMainFragment(): GitRepoListFragment
}