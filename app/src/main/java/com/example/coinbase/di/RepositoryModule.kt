package com.example.coinbase.di

import com.example.coinbase.data.repository.HomeRepositoryImpl
import com.example.coinbase.domain.repository.HomeRepository
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