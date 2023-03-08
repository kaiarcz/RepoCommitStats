package com.kaiarcz.repocommitstats.app.di

import com.kaiarcz.repocommitstats.data.remote.GithubApi
import com.kaiarcz.repocommitstats.data.repository.GitHubRepositoryImpl
import com.kaiarcz.repocommitstats.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.github.com"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(api: GithubApi): GitHubRepository {
        return GitHubRepositoryImpl(api)
    }

}