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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pidzama.shoppinlisttest.data.network.ListItem

@Composable
fun CardItemList(list: ListItem, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable {  },
        border = BorderStroke(3.dp, color = Color.Cyan),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Имя списка: ",
                    fontSize = 16.sp
                )
                Text(
                    text = list.name.toString(),
                    fontStyle = FontStyle.Italic
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Дата создания: ",
                    fontSize = 16.sp
                )
                Text(
                    text = list.created.toString(),
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewCardItem() {
    CardItemList(
        list =
        ListItem("22.22.22", "name", 1),
        navController = rememberNavController()
    )
}