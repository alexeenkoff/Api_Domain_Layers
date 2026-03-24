package ru.x5.stores.domain.model

data class Store(
    val id: StringId,
    val name: String,
    val address: String,
    val ts: TSType,
)
