package com.pidzama.shoppinlisttest.presentation.screens.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    private val _getKey = MutableLiveData<String>()
    val getKey: LiveData<String>
        get() = _getKey

    fun getAuthenticationKey() {
        viewModelScope.launch {
            shoppingRepository.getKey().let {
                if (it.isSuccessful) {
                    _getKey.postValue(it.body())
                } else {
                    it.errorBody()
                }
            }
        }
    }
}