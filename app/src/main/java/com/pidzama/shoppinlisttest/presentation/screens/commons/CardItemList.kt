package com.pidzama.shoppinlisttest.presentation.screens.commons

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.pidzama.shoppinlisttest.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pidzama.shoppinlisttest.data.network.CurrentList
import com.pidzama.shoppinlisttest.presentation.navigation.Screens

@Composable
fun CardItemList(list: CurrentList, navController: NavHostController) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .height(60.dp),
        border = BorderStroke(2.dp, color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp)
                    .weight(9f)
                    .clickable {
//                        navController.navigate(Screens.Details.route + "/${list.id}")
                        Toast.makeText(context, "${list.id}", Toast.LENGTH_SHORT).show()},
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = "Имя списка: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = list.name.toString(),
                    )
                }
                Row {
                    Text(
                        text = "Дата создания: ",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = list.created.toString(),
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable { },
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete icon"
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardItem() {
    CardItemList(
        list =
        CurrentList("22.22.22", "name", 1),
        navController = rememberNavController()
    )
}