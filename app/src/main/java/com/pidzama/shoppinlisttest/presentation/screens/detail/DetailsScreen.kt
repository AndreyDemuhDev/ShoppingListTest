package com.pidzama.shoppinlisttest.presentation.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.data.network.Elements
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import com.pidzama.shoppinlisttest.presentation.screens.commons.ButtonAddNewList
import com.pidzama.shoppinlisttest.presentation.screens.commons.CardElementItem
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogAddNewItemToShoppingList
import com.pidzama.shoppinlisttest.presentation.screens.commons.DialogDeleteList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(id: String, navController: NavHostController) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    val dialogDeleteState = remember { mutableStateOf(false) }
    val dialogAddNewItemState = remember { mutableStateOf(false) }
    val allElementsCurrentList = viewModel.getCurrentShoppingList.observeAsState(listOf()).value
    viewModel.removeItemFromList.observeAsState().value
    viewModel.addItemToList.observeAsState().value
    viewModel.removeList.observeAsState().value
    viewModel.crossedItOffItemToList.observeAsState().value
    viewModel.getCurrentShoppingList(id)

    if (dialogDeleteState.value) {
        DialogDeleteList(dialogDeleteState, onClickDelete = {
            viewModel.removeList(id)
            navController.navigate(Screens.Home.route)
        })
    }

    if (dialogAddNewItemState.value) {
        DialogAddNewItemToShoppingList(
            dialogAddNewItemState,
            addNewItem = { name, count ->
                viewModel.addItemToList(id, name, count)
            })
    }

    Scaffold(floatingActionButton = {
        ButtonAddNewList { dialogAddNewItemState.value = true }
    }) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 14.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    modifier = Modifier
                        .weight(9f)
                        .padding(horizontal = 2.dp),
                    text = "Список покупок: \n$id",
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
            Text(
                modifier = Modifier
                    .padding(horizontal = 2.dp),
                text = "Всего элементов: ${allElementsCurrentList.count()}, " +
                        "вычеркнуто: ${countCrossedElements(allElementsCurrentList).count()} ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis
            )
            LazyColumn(modifier = Modifier.padding(bottom = 60.dp, start = 1.dp, end = 8.dp)) {
                items(allElementsCurrentList) { element ->
                    CardElementItem(element = element, listId = id)
                }
            }
        }
    }
}

fun countCrossedElements(items: List<Elements>): List<Elements> {
    val listCrossedElements = mutableListOf<Elements>()
    items.forEach {
        if (it.isCrossed) {
            listCrossedElements.add(it)
        }
    }
    return listCrossedElements
}