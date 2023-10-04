package com.pidzama.shoppinlisttest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pidzama.shoppinlisttest.presentation.screens.authentication.AuthenticationScreen
import com.pidzama.shoppinlisttest.presentation.screens.detail.DetailsScreen
import com.pidzama.shoppinlisttest.presentation.screens.home.HomeScreen
import com.pidzama.shoppinlisttest.presentation.screens.splash.SplashScreen

@Composable
fun AppNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Authentication.route) {
            AuthenticationScreen(navController = navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.Details.route + "/{id}") { navBackStackEntry ->
            DetailsScreen(
                id = navBackStackEntry.arguments?.getString("id")?: "1",
                navController = navController
            )
        }
    }

}