package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.CurrentList
import com.pidzama.shoppinlisttest.data.network.Elements
import com.pidzama.shoppinlisttest.data.network.ShoppingListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    private val _createNewShoppingList = MutableLiveData<ShoppingListResponse>()
    val createNewShoppingList: MutableLiveData<ShoppingListResponse>
        get() = _createNewShoppingList

    private val _getAllShoppingLists = MutableLiveData<List<CurrentList>>()
    val getAllShoppingLists: MutableLiveData<List<CurrentList>>
        get() = _getAllShoppingLists

    private val _getCurrentShoppingList = MutableLiveData<Elements>()
    val getCurrentShoppingList: LiveData<Elements>
        get() = _getCurrentShoppingList


    fun getAllShoppingLists(key: String) {
        viewModelScope.launch {
            shoppingRepository.getAllMyShopLists(key).let {
                if (it.isSuccessful) {
                    _getAllShoppingLists.postValue(it.body()?.lists)
                } else {
                    it.errorBody()
                }
            }
        }
    }

    fun createNewShoppingList(key: String, name: String) {
        viewModelScope.launch {
            shoppingRepository.createNewShoppingList(key, name).let {
                if (it.isSuccessful) {
                    _createNewShoppingList.postValue(it.body())
                } else {
                    it.errorBody()
                }
            }
        }
    }

    fun getCurrentShoppingList(id: Int) {
        viewModelScope.launch {
            shoppingRepository.getShoppingList(id).let {
                if (it.isSuccessful) {
                    _getCurrentShoppingList.postValue(it.body())
                } else {
                    it.errorBody()
                }
            }
        }
    }
}