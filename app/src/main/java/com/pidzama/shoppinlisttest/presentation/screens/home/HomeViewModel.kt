package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

}