package com.pidzama.shoppinlisttest.data.network

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("CreateTestKey?") //получить ключ аутентификации
    suspend fun createTestKey(): Response<String>

    @POST("Authentication?") //авторизоваться по полученному ключу
    suspend fun authentication(@Query("key") key: String): Response<AuthResponse>

    @GET("GetAllMyShopLists?") // получить перечень списков покупок
    suspend fun getAllMyShopLists(@Query("key") key: String): Response<ShoppingListsItem>

    @POST("CreateShoppingList") //создать список покупок
    suspend fun createShoppingList(
        @Query("key") key: String,
        @Query("name") name: String
    ): Response<ShoppingListResponse>

    @GET("GetShoppingList?") //получить конкретный список
    suspend fun getShoppingList(@Query("list_id") id: String): Response<ShoppingList>

    @POST("RemoveShoppingList?") //удалить список покупок
    suspend fun removeShoppingList(@Query("list_id") id: String): Response<RemoveList>

    @POST("AddToShoppingList?") //добавить элемент в список покупок
    suspend fun addItemToShoppingList(
        @Query("id") id: String,
        @Query("value") value: String,
        @Query("n") n: String
    ): Response<AddElement>

    @POST("CrossItOff?") //удалить элемент из списка покупок
    suspend fun crossItOff(
        @Query("id") id: String,
    ): Response<CrossedElement>

    @POST("RemoveFromList?") //удалить элемент из списка покупок
    suspend fun removeFromList(
        @Query("list_id") listId: String,
        @Query("item_id") itemId: String,
    ): Response<DeleteElement>

}