package com.intuit.sendhil.gitexplorer.di.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.intuit.sendhil.gitexplorer.di.ui.ViewModelKey
import com.intuit.sendhil.gitexplorer.repo.Repository
import com.intuit.sendhil.gitexplorer.ui.list.GitRepoListFragment
import com.intuit.sendhil.gitexplorer.viewmodel.list.GitRepoListFeatureViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [GitRepoListFeatureModule.ProvideViewModel::class])
abstract class GitRepoListFeatureModule {


    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(GitRepoListFeatureViewModel::class)
        fun provideMainViewModel(repository: Repository): ViewModel = GitRepoListFeatureViewModel(repository)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideMainViewModel(
            factory: ViewModelProvider.Factory,
            target: GitRepoListFragment
        ): GitRepoListFeatureViewModel =
            ViewModelProviders.of(target, factory).get(GitRepoListFeatureViewModel::class.java)
    }

}