package com.example.coinbase.di

import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.domain.usecase.GetCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindUseCase(impl: GetCoinsUseCaseImpl): GetCoinsUseCase
}