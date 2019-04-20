package com.intuit.sendhil.gitexplorer.di

import android.content.Context
import com.intuit.sendhil.gitexplorer.GitExplorerApplication
import com.intuit.sendhil.gitexplorer.di.repository.RepositoryModule
import com.intuit.sendhil.gitexplorer.di.ui.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class, RepositoryModule::class, UiModule::class])
interface AppComponent {

    fun inject(application: GitExplorerApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: GitExplorerApplication): Builder

    }
}