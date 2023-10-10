package com.pidzama.shoppinlisttest.presentation.screens.commons

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DialogAddNewItemToShoppingList(
    dialogState: MutableState<Boolean>,
    addNewItem: (String, String) -> Unit,
) {
    val context = LocalContext.current
    val newItem = remember {
        mutableStateOf("")
    }
    val countItem = remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                if (newItem.value.isEmpty() || countItem.value.isEmpty()) {
                    Toast.makeText(context, "Все поля должны быть заполнены", Toast.LENGTH_SHORT)
                        .show()
                } else if (countItem.value.contains(Regex("[^0123456789]"))) {
                    Toast.makeText(
                        context,
                        "Поле \"Количество\" должно содержать только цифры",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    addNewItem(newItem.value, countItem.value)
                    dialogState.value = false
                }
            }
            ) {
                Text(text = "Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogState.value = false }
            ) {
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
                    value = countItem.value,
                    onValueChange = {
                        countItem.value = it
                    })
            }
        }
    )
}