package com.pidzama.shoppinlisttest.data.network

data class KeyResponse(
    val key: Int? = null,
)

data class CreateListResponse(
    val success: Boolean? = null,
    val list_id: Int? = null
)

data class PostAuth(
    val success: Boolean? = null
)
