package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.repository.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.CurrentList
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
}