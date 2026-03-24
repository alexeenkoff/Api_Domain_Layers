package org.example.domain.model

data class Store(
    val id: StringId,
    val name: String,
    val type: StoreType,
    val address: String,
    val ts: TSType,
)
