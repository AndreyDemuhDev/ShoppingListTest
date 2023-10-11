package com.pidzama.shoppinlisttest.presentation.screens.commons

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.data.network.Elements
import com.pidzama.shoppinlisttest.presentation.screens.detail.DetailsViewModel

@Composable
fun CardElementItem(element: Elements) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    val checked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
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
                        text = "Товар: ",
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
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

//            Image(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(all = 2.dp)
//                    .clickable {
//                        viewModel.crossedItOffItemFromList(element.id.toString())
//                    },
//                painter = painterResource(id = R.drawable.ic_check), contentDescription = null,
//                colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
//            )
            Checkbox(
                checked = checked.value,
                onCheckedChange = { viewModel.crossedItOffItemFromList(element.id.toString()) },
                modifier = Modifier.padding(5.dp),
                colors = if (isSystemInDarkTheme()) CheckboxDefaults.colors(Color.White) else CheckboxDefaults.colors(
                    Color.Black
                )
            )
        }
    }
}