package com.example.coinbase.di

import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.domain.usecase.GetCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindUseCase(impl: GetCoinsUseCaseImpl): GetCoinsUseCase
}