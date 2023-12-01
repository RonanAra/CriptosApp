package com.example.coinbase.di

import com.example.coinbase.data.api.HomeRepository
import com.example.coinbase.data.api.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(impl: HomeRepositoryImpl): HomeRepository
}