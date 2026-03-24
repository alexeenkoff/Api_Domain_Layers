package org.example.api.repository

import org.example.api.mapper.StoreMapper
import org.example.api.mocks.MockApi
import org.example.domain.model.Store

class StoresRepository(
    private val mockApi: MockApi = MockApi,
    private val mapper: StoreMapper = StoreMapper(),
) {
    fun getStores(): List<Store> {
        val apiStores = mockApi.getStores()
        return mapper.toDomain(apiStores)
    }
}
