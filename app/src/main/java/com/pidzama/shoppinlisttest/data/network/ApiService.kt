package com.pidzama.shoppinlisttest.data.network

import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @POST("CreateTestKey?")
    suspend fun createTestKey(): Response<String>

    @POST("Authentication?key=ADC4J5")
    @FormUrlEncoded
    suspend fun postAuthentication(@Field("key") key: String): Response<PostAuth>

    @POST("/CreateShoppingList?key=ADC4J5&name=")
    suspend fun postCreateShoppingList(@Body body: KeyResponse): Response<CreateListResponse>

//    @POST("/RemoveShoppingList?&list_id=290")
//    suspend fun postDeleteShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/AddToShoppingList?id=4&value=tools&n=3")
//    suspend fun postAddItemToShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/RemoveShoppingList?list_id=2")
//    suspend fun postRemoveItemFromShoppingList(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/GetAllMyShopLists?key=ADC4J5")
//    suspend fun postGetMyShoppingLists(@Body body: KeyResponse): Response<CreateListResponse>
//
//    @POST("/GetShoppingList?list_id=4")
//    suspend fun postGetShoppingListId(@Body body: KeyResponse): Response<CreateListResponse>
}