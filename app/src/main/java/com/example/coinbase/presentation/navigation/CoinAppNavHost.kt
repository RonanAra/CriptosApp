package com.example.coinbase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.presentation.detail.CoinDetailWebView
import com.example.coinbase.presentation.detail.viewmodel.WebSiteViewModel
import com.example.coinbase.presentation.home.HomeScreen
import com.example.coinbase.utils.serializableType
import kotlin.reflect.typeOf

@Composable
fun CoinAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                onClickCardItem = { coinModel ->
                    navController.navigate(Detail(coinModel))
                }
            )
        }
        composable<Detail>(
            typeMap = mapOf(typeOf<CoinModel>() to serializableType<CoinModel>())
        ) { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<Detail>()
            val viewModel: WebSiteViewModel = hiltViewModel(
                creationCallback = { factory: WebSiteViewModel.MyViewModelFactory ->
                    factory.create(detailRoute.model)
                }
            )
            CoinDetailWebView(
                viewModel = viewModel,
                navigationUpCallback = { navController.popBackStack() }
            )
        }
    }
}