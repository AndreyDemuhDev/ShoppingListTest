package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.data.network.ListItem

@Composable
fun CardItemList(list: ListItem, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable { },
        border = BorderStroke(2.dp, color = Color.Cyan),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Имя списка:")
            Text(text = list.name.toString())
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Дата создания:")
            Text(text = list.created.toString())
        }
    }
}