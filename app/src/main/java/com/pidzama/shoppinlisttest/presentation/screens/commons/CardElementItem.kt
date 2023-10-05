package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pidzama.shoppinlisttest.data.network.Elements

@Composable
fun CardElementItem(element: Elements) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .height(60.dp),
        border = BorderStroke(
            2.dp,
            color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp)
                    .weight(9f),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = "Наименование товара: ",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = element.name.toString(),
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row {
                    Text(
                        text = "Количество: ",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = element.created.toString(),
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}