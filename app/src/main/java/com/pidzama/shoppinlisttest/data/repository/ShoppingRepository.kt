package com.pidzama.shoppinlisttest.data.repository

import com.pidzama.shoppinlisttest.data.network.ApiService
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getKey() = apiService.createTestKey()

    suspend fun authentication(key: String) = apiService.authentication(key = key)

    suspend fun createNewShoppingList(key: String, name: String) =
        apiService.createShoppingList(key = key, name = name)

    suspend fun getAllMyShopLists(key: String) = apiService.getAllMyShopLists(key = key)

    suspend fun getShoppingList(id: String) = apiService.getShoppingList(id = id)

    suspend fun removeShoppingList(id: String) = apiService.removeShoppingList(id = id)

    suspend fun addItemToShoppingList(id: String, value: String, n: String) =
        apiService.addItemToShoppingList(id = id, value = value, n = n)

    suspend fun crossedItOff(id: String) = apiService.crossItOff(id = id)
}
