package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun DialogAddNameList(dialogState: MutableState<Boolean>, onSaveNameList: (String) -> Unit) {
    val nameList = remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onSaveNameList(nameList.value)
                dialogState.value = false
            }
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogState.value = false }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Введите имя списка:")
                TextField(value = nameList.value, onValueChange = {
                    nameList.value = it
                })
            }
        }
    )
}