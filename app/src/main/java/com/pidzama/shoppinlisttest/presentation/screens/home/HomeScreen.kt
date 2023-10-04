package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.foundation.lazy.items
import com.pidzama.shoppinlisttest.presentation.screens.commons.ButtonAddNewList
import com.pidzama.shoppinlisttest.presentation.screens.commons.CardItemList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogAddNameList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogDeleteList
import com.pidzama.shoppinlisttest.presentation.ui.theme.ShoppinListTestTheme

@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel = hiltViewModel<HomeViewModel>()
    val dataStoreRepository = DataStoreRepository(context)
    val getKey = dataStoreRepository.getKey().collectAsState(initial = "")
    val allShoppingLists = viewModel.getAllShoppingLists.observeAsState(listOf()).value
    val dialogState = remember { mutableStateOf(false) }
    val dialogDeleteState = remember { mutableStateOf(false) }
    viewModel.getAllShoppingLists(getKey.value)
    viewModel.getAllShoppingLists.observeAsState(listOf()).value
    if (dialogState.value) {
        DialogAddNameList(dialogState, onSaveNameList = {
            viewModel.createNewShoppingList(key = getKey.value, name = it)
        })
    }
    if (dialogDeleteState.value){
        DialogDeleteList(dialogDeleteState, onClickDelete = {

        })
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(floatingActionButton = {
            ButtonAddNewList { dialogState.value = true }
        }) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp),
            ) {
                Text(
                    text = "Списки пользователя ${getKey.value}",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
                LazyColumn {
                    items(allShoppingLists) { item ->
                        CardItemList(list = item, navController = navController)
                    }
                }
            }
        }
    }
}
