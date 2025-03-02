package com.example.coinbase.viewmodel

import app.cash.turbine.test
import com.example.coinbase.dispatcher.MainDispatcherRule
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.mockData.MockData
import com.example.coinbase.presentation.home.HomeEvent
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel
import com.example.coinbase.utils.exceptions.RetrofitException
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
    fun `should show list of coins when collecting starts`() = runTest {
        val coinsMock = MockData.coins
        coEvery { getCoinsUseCase() } returns coinsMock

        homeViewModel.onEvent(HomeEvent.LoadCoins)

        homeViewModel.uiState.test {
            assertEquals(
                coinsMock,
                awaitItem().coins
            )
        }

        coVerify { getCoinsUseCase() }
    }

    @Test
    fun `test state error when fetching coins list`() = runTest {
        coEvery { getCoinsUseCase() } throws MockData.errorException

        homeViewModel.onEvent(HomeEvent.LoadCoins)

        homeViewModel.uiState.test {
            assert(awaitItem().errorMessage?.isNotEmpty() == true)
        }

        coVerify { getCoinsUseCase() }
    }

    @Test(expected = RetrofitException::class)
    fun `should throw an exception when the calling load coins event returns an exception`() =
        runTest {
            coEvery { getCoinsUseCase() } throws RetrofitException("", null)

            homeViewModel.onEvent(HomeEvent.LoadCoins)

            coVerify { getCoinsUseCase() }
        }
}