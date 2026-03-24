package org.example.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    @SerialName("storeId")
    val storeId: String,

    @SerialName("storeName")
    val storeName: String,

    @SerialName("storeType")
    val storeType: String,

    @SerialName("address")
    val address: String,

    @SerialName("ts")
    val ts: String
)
