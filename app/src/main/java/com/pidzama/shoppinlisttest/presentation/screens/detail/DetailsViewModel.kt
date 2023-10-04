package com.pidzama.shoppinlisttest.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import com.pidzama.shoppinlisttest.data.network.AddElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
): ViewModel() {

    private val _addItemToList = MutableLiveData<AddElement>()
    val addItemToList: LiveData<AddElement>
    get() = _addItemToList

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
}