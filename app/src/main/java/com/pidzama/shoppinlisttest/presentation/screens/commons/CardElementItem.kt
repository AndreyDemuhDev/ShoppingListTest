package com.pidzama.shoppinlisttest.presentation.screens.commons

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.data.network.Elements
import com.pidzama.shoppinlisttest.presentation.screens.detail.DetailsViewModel

@Composable
fun CardElementItem(element: Elements, listId: String) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    val checked = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clickable {
                Toast
                    .makeText(context, "${element.id} + ${element.isCrossed}", Toast.LENGTH_SHORT)
                    .show()
            },
        border = BorderStroke(
            2.dp,
            color = when {
                isSystemInDarkTheme() && element.isCrossed ||
                        !isSystemInDarkTheme() && element.isCrossed -> Color.Red
                isSystemInDarkTheme() -> Color.White
                else -> Color.DarkGray
            }
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
                    .weight(10f),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Text(
                        text = "Товар: ",
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = element.name.toString(),
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (element.isCrossed) {
                            TextDecoration.LineThrough
                        } else TextDecoration.None
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
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Checkbox(
                checked = if (element.isCrossed) !checked.value else checked.value,
                onCheckedChange = {
                    checked.value = it
                    viewModel.crossedItOffItemFromList(element.id.toString())
                },
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f),
                colors = if (isSystemInDarkTheme()) CheckboxDefaults.colors(Color.White) else CheckboxDefaults.colors(
                    Color.Black
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete icon",
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.removeItemFromList(listId, element.id.toString()) }
            )
        }
    }
}