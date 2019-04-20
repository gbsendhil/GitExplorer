package com.intuit.sendhil.gitexplorer.di.repository

import com.intuit.sendhil.gitexplorer.api.GitHubService
import com.intuit.sendhil.gitexplorer.repo.Repository
import com.intuit.sendhil.gitexplorer.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(gitHubService: GitHubService): Repository = RepositoryImpl(gitHubService)
}