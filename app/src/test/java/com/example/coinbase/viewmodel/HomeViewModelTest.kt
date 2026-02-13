package com.example.coinbase.viewmodel

import app.cash.turbine.test
import com.example.coinbase.dispatcher.MainDispatcherRule
import com.example.coinbase.domain.usecase.GetCoinsUseCase
import com.example.coinbase.mockData.MockData
import com.example.coinbase.presentation.home.HomeEvent
import com.example.coinbase.presentation.home.viewmodel.HomeUiState
import com.example.coinbase.presentation.home.viewmodel.HomeViewModel
import com.example.coinbase.utils.exceptions.RetrofitException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
    fun `should validate initial state`() = runTest {
        homeViewModel.uiState.test {
            assertEquals(HomeUiState.Uninitialized, awaitItem())
        }
    }

    @Test
    fun `should show list of coins when collecting starts`() = runTest {
        val coinsMock = MockData.coins
        coEvery { getCoinsUseCase() } returns coinsMock

        homeViewModel.uiState.test {
            skipItems(1)

            homeViewModel.onEvent(HomeEvent.LoadCoins)

            advanceUntilIdle()

            assertEquals(
                HomeUiState.Loaded(coinsMock),
                awaitItem()
            )
        }

        coVerify { getCoinsUseCase() }
    }

    @Test
    fun `test state error when fetching coins list`() = runTest {
        coEvery { getCoinsUseCase() } throws MockData.errorException

        homeViewModel.onEvent(HomeEvent.LoadCoins)

        homeViewModel.uiState.test {
            awaitItem() as HomeUiState.Error
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