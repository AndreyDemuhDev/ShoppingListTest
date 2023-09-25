package com.pidzama.shoppinlisttest.presentation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pidzama.shoppinlisttest.presentation.ui.theme.ShoppinListTestTheme

@Composable
fun HomeScreen() {
    Text(text = "Hello")
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ShoppinListTestTheme {
        HomeScreen()
    }
}