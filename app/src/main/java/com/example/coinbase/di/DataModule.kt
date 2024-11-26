package com.example.coinbase.di

import com.example.coinbase.data.datasource.HomeDataSource
import com.example.coinbase.data.datasource.HomeDataSourceImpl
import com.example.coinbase.data.repository.HomeRepositoryImpl
import com.example.coinbase.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindHomeDataSource(impl: HomeDataSourceImpl): HomeDataSource
}