package com.pidzama.shoppinlisttest.presentation.screens.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.PostAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    private val _getKey = MutableLiveData<String>()
    val getKey: LiveData<String>
        get() = _getKey

    private val _key = MutableLiveData<PostAuth>()
    val key: LiveData<PostAuth>
        get() = _key

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