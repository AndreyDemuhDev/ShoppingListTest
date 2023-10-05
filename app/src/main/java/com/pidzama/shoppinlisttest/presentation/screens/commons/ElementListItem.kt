package com.pidzama.shoppinlisttest.presentation.screens.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.pidzama.shoppinlisttest.R
import com.pidzama.shoppinlisttest.data.network.Elements

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ElementListItem(text: String) {
    ListItem(
        text = { Text(text = text + "1") },
        overlineText = { Text(text = "TExt2") },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "delete swipe icon"
            )
        },
        trailing = {
            Icon(
                imageVector = Icons.Default.Close, contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    )


}