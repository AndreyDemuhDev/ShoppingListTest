package com.pidzama.shoppinlisttest.presentation.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pidzama.shoppinlisttest.data.repository.DataStoreRepository
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun AuthenticationScreen(navController: NavHostController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStoreRepository = DataStoreRepository(context)
    val getKey = dataStoreRepository.getKey().collectAsState(initial = "")
    val viewModel = hiltViewModel<AuthViewModel>()
    val authentication = remember { mutableStateOf("") }
    val key = remember { mutableStateOf("______") }
    viewModel.getAuthenticationKey()

    val screenKey = viewModel.getKey.value

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(start = 50.dp, end = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = key.value,
                fontSize = 36.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
            )
            OutlinedButton(
                border = BorderStroke(
                    width = 2.dp,
                    color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                ),
                onClick = {
                    key.value = screenKey.toString()
                }) {
                Text(
                    textAlign = TextAlign.Center,
                    color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
                    text = "Получить ключ"
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = authentication.value,
                onValueChange = { authentication.value = it },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth(),
                label = {Text(text = "Введите ключ аунтефикации") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = getKey.value,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
            )
            Button(enabled = authentication.value.length >= 6,
                onClick = {
                    viewModel.authentication(authentication.value)
                    if (viewModel.key.value?.success == true) {
                        scope.launch {
                            dataStoreRepository.saveAuthKey(authentication.value)
                        }
                        viewModel.authenticationState(completed = true)
                        navController.navigate(Screens.Home.route)
                        Toast.makeText(
                            context,
                            viewModel.key.value?.success.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            viewModel.key.value?.error.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }) {
                Text(
                    text = "Авторизоваться",
                    color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    AuthenticationScreen(NavHostController(LocalContext.current))
}