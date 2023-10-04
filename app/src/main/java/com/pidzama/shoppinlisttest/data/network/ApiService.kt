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
    suspend fun getShoppingList(@Query("list_id") id: String): Response<Elements>

    @POST("RemoveShoppingList?")
    suspend fun removeShoppingList(@Query("list_id") id: String): Response<RemoveList>

    @POST("AddToShoppingList?")
    suspend fun addItemToShoppingList(
        @Query("id") id: String,
        @Query("value") value: String,
        @Query("n") n: String
    ): Response<AddElement>


//    @POST("/RemoveShoppingList?&list_id=290")
//    suspend fun postDeleteShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/AddToShoppingList?id=4&value=tools&n=3")
//    suspend fun postAddItemToShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/RemoveShoppingList?list_id=2")
//    suspend fun postRemoveItemFromShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//
//    @POST("/GetShoppingList?list_id=4")
//    suspend fun postGetShoppingListId(@Body body: KeyResponse): Response<CreateListResponse>
}