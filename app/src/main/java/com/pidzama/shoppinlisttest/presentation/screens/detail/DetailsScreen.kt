package com.pidzama.shoppinlisttest.presentation.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogAddNewItemToShoppingList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogDeleteList
import com.pidzama.shoppinlisttest.presentation.screens.home.HomeViewModel

@Composable
fun DetailsScreen(id: String, navController: NavHostController) {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val detailViewModel = hiltViewModel<DetailsViewModel>()
    val dialogDeleteState = remember { mutableStateOf(false) }
    val dialogAddNewItemState = remember { mutableStateOf(false) }

    val currentList = homeViewModel.getCurrentShoppingList.observeAsState().value
    val currentItems = detailViewModel.addItemToList.observeAsState().value
    homeViewModel.getCurrentShoppingList(id)

    if (dialogDeleteState.value) {
        DialogDeleteList(dialogDeleteState, onClickDelete = {
            homeViewModel.removeList(id)
            navController.navigate(Screens.Home.route)
        })
    }
    if (dialogAddNewItemState.value) {
        DialogAddNewItemToShoppingList(
            dialogDeleteState,
            addNewItem = {

            },
            addCount = {

            })
    }


    Row {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    modifier = Modifier
                        .weight(9f)
                        .padding(horizontal = 10.dp),
                    text = "Список покупок: \n$id, ${currentList?.name.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                        .clickable { dialogDeleteState.value = true },
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete icon"
                )
            }
            Button(onClick = { dialogAddNewItemState.value = true }) {
                Text(text = "Добавить товар")
            }
        }
    }
}