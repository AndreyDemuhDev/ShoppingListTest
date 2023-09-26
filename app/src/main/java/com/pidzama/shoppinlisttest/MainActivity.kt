package com.pidzama.shoppinlisttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.pidzama.shoppinlisttest.presentation.navigation.AppNavGraph
import com.pidzama.shoppinlisttest.presentation.ui.theme.ShoppinListTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppinListTestTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}
