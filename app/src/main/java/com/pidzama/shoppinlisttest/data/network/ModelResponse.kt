package com.pidzama.shoppinlisttest.data.network

import com.google.gson.annotations.SerializedName

//при успешной авторизации получаем ответ
data class AuthResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("error")
    val error: String? = null
)

data class ShoppingListResponse(
    @SerializedName("key")
    var success: Boolean,
    @SerializedName("list_id")
    var id: Int
)

//перечень всех списков покупок по конкретному ключу
data class ShoppingListsItem(
    @SerializedName("shop_list")
    val lists: List<CurrentList> = listOf(),
    val success: Boolean? = null
)

//информация по одному списку
data class CurrentList(
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null,
)

//перечень конкретного списка
data class ShoppingList(
    val success: Boolean? = null,
    @SerializedName("item_list")
    val lists: List<Elements> = listOf()
)

//элементы списка покупок
data class Elements(
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("is_crossed")
    val isCrossed: Boolean,
    @SerializedName("id")
    val id: Int? = null,
)

data class RemoveList(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("new_value")
    val delete: Boolean
)
//возвращаем такой ответ при добавлении элемента в список покупок
data class AddElement(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("item_id")
    val itemId: Int
)
