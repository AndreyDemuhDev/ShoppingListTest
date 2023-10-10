package com.pidzama.shoppinlisttest.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.repository.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.AddElement
import com.pidzama.shoppinlisttest.data.network.Elements
import com.pidzama.shoppinlisttest.data.network.RemoveList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
): ViewModel() {

    private val _getCurrentShoppingList = MutableLiveData<List<Elements>>()
    val getCurrentShoppingList: LiveData<List<Elements>>
        get() = _getCurrentShoppingList

    private val _addItemToList = MutableLiveData<AddElement>()
    val addItemToList: LiveData<AddElement>
    get() = _addItemToList

    private val _removeList = MutableLiveData<RemoveList>()
    val removeList: LiveData<RemoveList>
        get() = _removeList

    fun addItemToList(id: String, value: String, count: String){
        viewModelScope.launch {
            shoppingRepository.addItemToShoppingList(id, value, count).let{
                if (it.isSuccessful){
                    _addItemToList.postValue(it.body())
                } else{
                    it.errorBody()
                }
            }
        }
    }

    fun getCurrentShoppingList(id: String) {
        viewModelScope.launch {
            shoppingRepository.getShoppingList(id).let {
                if (it.isSuccessful) {
                    _getCurrentShoppingList.postValue(it.body()?.lists)
                } else {
                    it.errorBody()
                }
            }
        }
    }

    fun removeList(id: String) {
        viewModelScope.launch {
            shoppingRepository.removeShoppingList(id).let {
                if (it.isSuccessful) {
                    _removeList.postValue(it.body())
                } else {
                    it.errorBody()
                }
            }
        }
    }
}