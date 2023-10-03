package com.pidzama.shoppinlisttest.data

import com.pidzama.shoppinlisttest.data.network.ApiService
import com.pidzama.shoppinlisttest.data.network.ShoppingListRequest
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getKey() = apiService.createTestKey()

    suspend fun authentication(key: String) = apiService.authentication(key = key)

    suspend fun createNewShoppingList(key: String, name: String) = apiService.createShoppingList(key = key, name = name)
}
