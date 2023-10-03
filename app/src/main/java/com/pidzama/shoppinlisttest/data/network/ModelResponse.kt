package com.pidzama.shoppinlisttest.data.network

import com.google.gson.annotations.SerializedName

//при успешной авторизации получаем ответ
data class AuthResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("error")
    val error: String? = null
)

data class ShoppingListRequest(
    @SerializedName("key")
    var key: String,
    @SerializedName("name")
    var name: String
)

data class ShoppingListResponse(
    @SerializedName("key")
    var success: Boolean,
    @SerializedName("list_id")
    var id: Int
)



data class ShoppingListsItem(
    @SerializedName("shop_list")
    val lists: ArrayList<ListItem> = arrayListOf(),
    val success: Boolean? = null
)

data class ListItem(
    val created: String? = null,
    val name: String? = null,
    val id: Int? = null,
)
