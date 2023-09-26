package com.pidzama.shoppinlisttest.presentation.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Authentication : Screens("authentication_screen")
    object Home : Screens("home_screen")
}