package com.pidzama.shoppinlisttest.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.pidzama.shoppinlisttest.data.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

//    private val _getAllShoppingLists = MutableLiveData<List<ListItem>>()
//    val getAllShoppingLists: MutableLiveData<List<ListItem>>
//        get() = _getAllShoppingLists
//
//    fun getAllShoppingLists(key: String) {
//        viewModelScope.launch {
//            shoppingRepository.getAllMyShopLists(key).let {
//                if (it.isSuccessful) {
//                    _getAllShoppingLists.postValue(it.body()?.lists)
//                } else {
//                    it.errorBody()
//                }
//            }
//        }
//    }
}