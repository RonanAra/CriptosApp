package com.example.coinbase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.presentation.detail.CoinDetailWebView
import com.example.coinbase.presentation.detail.viewmodel.WebSiteViewModel
import com.example.coinbase.presentation.home.HomeScreen
import com.example.coinbase.utils.decodeObjectToArgs
import com.example.coinbase.utils.encodeObjectToArgs

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
                onClickCardItem = { coinModel ->
                    navController.navigate("${Detail.route}/${coinModel.encodeObjectToArgs()}")
                }
            )
        }
        composable(
            route = Detail.routeWithArgs,
            arguments = Detail.arguments
        ) { backStackEntry ->
            val argument = backStackEntry.arguments?.getString(Detail.coinTypeArg)
            val coinModel = argument.decodeObjectToArgs<CoinModel>()
            coinModel?.let { model ->
                val viewModel: WebSiteViewModel = hiltViewModel(
                    creationCallback = { factory: WebSiteViewModel.MyViewModelFactory ->
                        factory.create(model)
                    }
                )
                CoinDetailWebView(
                    viewModel = viewModel,
                    navigationUpCallback = { navController.popBackStack() }
                )
            }
        }
    }
}