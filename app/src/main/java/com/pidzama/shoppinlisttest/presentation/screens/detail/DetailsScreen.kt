package com.pidzama.shoppinlisttest.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.presentation.screens.home.HomeViewModel

@Composable
fun DetailsScreen(id: String, navController: NavHostController) {

    val viewModel = hiltViewModel<HomeViewModel>()
    val currentList = viewModel.getCurrentShoppingList.observeAsState().value
    viewModel.getCurrentShoppingList(id)

    Column {
        Text(text = "Экран списка покупок $id")
    }

}