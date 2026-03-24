package ru.x5.stores

import ru.x5.stores.domain.usecase.StoresUsecase

fun main() {
    val usecase = StoresUsecase()
    val stores = usecase.getStores()

    println("=== Магазины ===")
    println()
    stores.forEach { store ->
        println("ID: ${store.id.value}")
        println("Название: ${store.name}")
        println("Адрес: ${store.address}")
        println("ТС: ${store.ts}")
        println()
    }

    println("Всего магазинов: ${stores.size}")
}
