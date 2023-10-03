package com.pidzama.shoppinlisttest.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1000)
        navController.popBackStack()
        navController.navigate(Screens.Authentication.route)
    }
    ViewSplash(alpha = alphaAnim.value)
}

@Composable
fun ViewSplash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.LightGray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(150.dp)
                    .alpha(alpha = alpha),
                imageVector = Icons.Default.List,
                contentDescription = "splash logo",
                tint = if (isSystemInDarkTheme()) Color.White else Color.DarkGray)
            Text(modifier = Modifier.alpha(alpha = alpha),
                text = "Shopping List",
                fontSize = 32.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray)
        }
    }
}