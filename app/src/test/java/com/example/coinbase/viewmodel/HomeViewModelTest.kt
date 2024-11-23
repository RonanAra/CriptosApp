package com.example.coinbase.viewmodel

import app.cash.turbine.test
import com.example.coinbase.dispatcher.MainDispatcherRule
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.mockData.MockData
import com.example.coinbase.presentation.home.viewmodel.HomeUiState
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCoinsUseCase: GetCoinsUseCase = mockk(relaxed = true)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(getCoinsUseCase)
    }

    @Test
    fun `test initial state is ShowLoading`() = runTest {
        assertEquals(
            homeViewModel.uiState.value,
            HomeUiState.Loading
        )
    }

    @Test
    fun `should show list of coins when collecting starts`() = runTest {
        val coinsMock = MockData.coins
        coEvery { getCoinsUseCase() } returns coinsMock

        homeViewModel.uiState.test {
            assertEquals(
                HomeUiState.ListCoins(coinsMock),
                awaitItem()
            )
        }

        coVerify { getCoinsUseCase() }
    }
}