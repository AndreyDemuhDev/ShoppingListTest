package com.pidzama.shoppinlisttest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pidzama.shoppinlisttest.presentation.screens.authentication.AuthenticationScreen
import com.pidzama.shoppinlisttest.presentation.screens.home.HomeScreen
import com.pidzama.shoppinlisttest.presentation.screens.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.Authentication.route){
            AuthenticationScreen(navController = navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
    }

}