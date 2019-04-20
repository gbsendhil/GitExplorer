package com.intuit.sendhil.gitexplorer.di

import com.intuit.sendhil.gitexplorer.api.GitHubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {


    private const val API_URL = "https://api.github.com"

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    @JvmStatic
    fun provideGitHubService(retrofit: Retrofit): GitHubService =
        retrofit.create<GitHubService>(GitHubService::class.java)


}