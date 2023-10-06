package com.pidzama.shoppinlisttest.presentation.screens.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.repository.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.AuthResponse
import com.pidzama.shoppinlisttest.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _getKey = MutableLiveData<String>()
    val getKey: LiveData<String>
        get() = _getKey

    private val _key = MutableLiveData<AuthResponse>()
    val key: LiveData<AuthResponse>
        get() = _key

    fun authenticationState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.authenticationState(completed = completed)
        }
    }

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

    fun authentication(key: String) {
        viewModelScope.launch {
            shoppingRepository.authentication(key).let {
                if (it.isSuccessful) {
                    _key.postValue(it.body())
                } else {
                    it.errorBody()
                }
            }
        }
    }
}