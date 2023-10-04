package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogAddNewItemToShoppingList(
    dialogState: MutableState<Boolean>,
    addNewItem: (String) -> Unit,
    addCount: (Int) -> Unit
) {
    val newItem = remember {
        mutableStateOf("")
    }
    val countItem = remember {
        mutableStateOf(0)
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                addNewItem(newItem.value)
                addCount(countItem.value)
                dialogState.value = false
            }
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogState.value = false }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Введите имя товара:")
                TextField(
                    value = newItem.value,
                    onValueChange = {
                        newItem.value = it
                    })
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Введите количество товара:")
                TextField(
                    value = countItem.value.toString(),
                    onValueChange = {
                        countItem.value = it.toInt()
                    })
            }
        }
    )
}