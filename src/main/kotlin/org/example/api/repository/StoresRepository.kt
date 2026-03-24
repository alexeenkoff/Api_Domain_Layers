package ru.x5.stores.api.repository

import ru.x5.stores.api.mapper.StoreMapper
import ru.x5.stores.api.mocks.MockApi
import ru.x5.stores.domain.model.Store

class StoresRepository(
    private val mockApi: MockApi = MockApi,
    private val mapper: StoreMapper = StoreMapper(),
) {
    fun getStores(): List<Store> {
        val apiStores = mockApi.getStores()
        return mapper.toDomain(apiStores)
    }
}
