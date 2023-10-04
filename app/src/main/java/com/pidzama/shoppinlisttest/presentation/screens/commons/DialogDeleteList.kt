package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun DialogDeleteList(dialogState: MutableState<Boolean>, onClickDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            dialogState.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                onClickDelete()
                dialogState.value = false
            }
            ) {
                Text(text = "Удалить")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogState.value = false }) {
                Text(text = "Отмена")
            }
        },
        title = {
            Column {
                Text(text = "Действительно хотите удалить лист?")
            }
        }
    )
}