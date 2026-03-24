package org.example.api.mapper

import org.example.api.model.StoreResponse
import org.example.domain.model.Store
import org.example.domain.model.StoreType
import org.example.domain.model.StringId
import org.example.domain.model.TSType

class StoreMapper {
    fun toDomain(api: StoreResponse): Store =
        Store(
            id = StringId(api.storeId),
            name = api.storeName,
            type = StoreType.valueOf(api.storeType),
            address = api.address,
            ts = TSType.valueOf(api.ts),
        )

    fun toDomain(apiList: List<StoreResponse>): List<Store> = apiList.map { toDomain(it) }
}
