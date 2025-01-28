package com.jdbarad.jaypalsinhbarad.data.di

import com.jdbarad.jaypalsinhbarad.data.remote.Repository
import com.jdbarad.jaypalsinhbarad.data.remote.ktorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object HiltProvider {

    @Provides
    fun provideKtorClient():HttpClient  {
        return ktorClient()
    }

    @Provides
    fun provideRepository(client: HttpClient): Repository {
        return Repository(client)
    }

}