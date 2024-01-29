package com.example.coinbase.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavDestination {
    val route: String
}

object Home : NavDestination {
    override val route: String = "home"
}

object Detail : NavDestination {
    override val route: String = "detail"
    const val coinTypeArg = "coin_model"
    val routeWithArgs = "${route}/{$coinTypeArg}"
    val arguments = listOf(
        navArgument(coinTypeArg) { type = NavType.StringType }
    )
}