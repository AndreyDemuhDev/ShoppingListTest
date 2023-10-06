package com.pidzama.shoppinlisttest.presentation.screens.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.repository.DataStoreRepository
import com.pidzama.shoppinlisttest.presentation.navigation.Screens
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screens.Authentication.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnAuthState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screens.Home.route
                } else {
                    _startDestination.value = Screens.Authentication.route
                }
            }
            _isLoading.value = false
        }
    }
}