package com.pidzama.shoppinlisttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.pidzama.shoppinlisttest.presentation.navigation.AppNavGraph
import com.pidzama.shoppinlisttest.presentation.screens.splash.SplashScreenViewModel
import com.pidzama.shoppinlisttest.presentation.ui.theme.ShoppinListTestTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        !splashScreenViewModel.isLoading.value
        setContent {
            ShoppinListTestTheme {
                val startScreen by splashScreenViewModel.startDestination
                val navController = rememberNavController()
                AppNavGraph(
                    startDestination = startScreen,
                    navController = navController
                )
            }
        }
    }
}
