package ru.x5.stores.domain.usecase

import ru.x5.stores.api.repository.StoresRepository
import ru.x5.stores.domain.model.Store

class StoresUsecase(
    private val repository: StoresRepository = StoresRepository(),
) {
    fun getStores(): List<Store> = repository.getStores()
}
