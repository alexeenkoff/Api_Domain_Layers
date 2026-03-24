package org.example.api.mocks

import org.example.api.model.StoreResponse

object MockApi {
    fun getStores(): List<StoreResponse> =
        listOf(
            StoreResponse(
                storeId = "store-001",
                storeName = "Пятерочка - Тверская",
                storeType = "TSX",
                address = "г. Москва, ул. Тверская, 1",
                ts = "TSX",
            ),
            StoreResponse(
                storeId = "store-002",
                storeName = "Перекресток - Арбат",
                storeType = "TS5",
                address = "г. Москва, ул. Арбат, 5",
                ts = "TS5",
            ),
            StoreResponse(
                storeId = "store-003",
                storeName = "Чижик - Садовая",
                storeType = "TSH",
                address = "г. Москва, ул. Садовая, 3",
                ts = "TSC",
            ),
        )
}
