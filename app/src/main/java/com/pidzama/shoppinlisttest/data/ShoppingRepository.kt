package com.pidzama.shoppinlisttest.data

import com.pidzama.shoppinlisttest.data.network.ApiService
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getKey() = apiService.createTestKey()

    suspend fun authentication(key: String) = apiService.authentication(key = key)
}