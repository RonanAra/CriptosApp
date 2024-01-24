package com.example.coinbase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinbase.presentation.detail.CoinDetailWebView
import com.example.coinbase.presentation.detail.viewmodel.WebSiteViewModel
import com.example.coinbase.presentation.home.HomeScreen
import com.example.coinbase.utils.encodeUrl

@Composable
fun CoinAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onClickCardItem = { url ->
                    navController.navigate("${Detail.route}/${url.encodeUrl()}")
                }
            )
        }
        composable(
            route = Detail.routeWithArgs,
            arguments = Detail.arguments
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString(Detail.urlTypeArg)
            val viewModel: WebSiteViewModel = hiltViewModel(
                creationCallback = { factory: WebSiteViewModel.MyViewModelFactory ->
                    factory.create(url.orEmpty())
                }
            )
            CoinDetailWebView(viewModel = viewModel)
        }
    }
}