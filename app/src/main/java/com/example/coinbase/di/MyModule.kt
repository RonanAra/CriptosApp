package com.example.coinbase.di

import com.example.coinbase.api.Repository
import com.example.coinbase.features.home.usecase.HomeUseCase
import com.example.coinbase.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val mainModule = module {
        single { Repository() }
        factory { HomeUseCase(repository = get()) }

        viewModel { HomeViewModel(homeUseCase = get()) }
    }

