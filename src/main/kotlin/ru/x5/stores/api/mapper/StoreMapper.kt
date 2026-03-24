package ru.x5.stores.api.mapper

import ru.x5.stores.api.model.StoreResponse
import ru.x5.stores.domain.model.Store
import ru.x5.stores.domain.model.StringId
import ru.x5.stores.domain.model.TSType

class StoreMapper {
    fun toDomain(api: StoreResponse): Store =
        Store(
            id = StringId(api.storeId),
            name = api.storeName,
            address = api.address,
            ts = TSType.valueOf(api.ts),
        )

    fun toDomain(apiList: List<StoreResponse>): List<Store> = apiList.map { toDomain(it) }
}
