package com.pidzama.shoppinlisttest.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.data.repository.DataStoreRepository
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import com.pidzama.shoppinlisttest.presentation.screens.commons.ButtonAddNewList
import com.pidzama.shoppinlisttest.presentation.screens.commons.CardItemList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogAddNameList
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<HomeViewModel>()
    val dataStoreRepository = DataStoreRepository(context)
    val getKey = dataStoreRepository.getKey().collectAsState(initial = "")
    val allShoppingLists = viewModel.getAllShoppingLists.observeAsState(listOf()).value
    val dialogState = remember { mutableStateOf(false) }
    viewModel.getAllShoppingLists(getKey.value)
    viewModel.createNewShoppingList.observeAsState().value
    viewModel.getAllShoppingLists.observeAsState(listOf()).value

    if (dialogState.value) {
        DialogAddNameList(dialogState, onSaveNameList = {
            viewModel.createNewShoppingList(key = getKey.value, name = it)
        })
    }

    Scaffold(floatingActionButton = {
        ButtonAddNewList { dialogState.value = true }
    }) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Все списки: ${getKey.value}",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
                Icon(
                    painter = painterResource(R.drawable.ic_exit_app),
                    contentDescription = "exit app icon",
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.Authentication.route){
                            popUpTo(navController.graph.id){
                                inclusive = true
                            }
                        }
                        scope.launch {
                            dataStoreRepository.deleteKey()
                        }
                    }
                )
            }
            LazyColumn {
                items(allShoppingLists) { item ->
                    CardItemList(list = item, navController = navController)
                }
            }
        }
    }
}