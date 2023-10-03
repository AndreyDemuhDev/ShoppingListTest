package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pidzama.shoppinlisttest.data.DataStoreRepository
import com.pidzama.shoppinlisttest.presentation.screens.authentication.AuthViewModel
import com.pidzama.shoppinlisttest.presentation.screens.commons.ButtonAddNewList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogAddNameList
import com.pidzama.shoppinlisttest.presentation.ui.theme.ShoppinListTestTheme

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current
    val viewModel = hiltViewModel<AuthViewModel>()
    val dataStoreRepository = DataStoreRepository(context)
    val getKey = dataStoreRepository.getKey().collectAsState(initial = "")
    val dialogState = remember { mutableStateOf(false) }
    if (dialogState.value) {
        DialogAddNameList(dialogState, onSaveNameList = {

        })
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
    ) {
        Scaffold(floatingActionButton = {
            ButtonAddNewList { dialogState.value = true }
        }) {
            Column(
                modifier = Modifier.padding(start = 10.dp),
            ) {
                Text(
                    text = "Списки пользователя ${getKey.value}",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
                LazyColumn(modifier = Modifier.background(color = Color.Black)) {
//                    items(allShoppingLists) { item ->
//                        CardItemList(list = item, navController = navController)
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ShoppinListTestTheme {
        HomeScreen()
    }
}