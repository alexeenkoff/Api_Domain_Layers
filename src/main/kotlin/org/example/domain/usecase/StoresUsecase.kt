package org.example.domain.usecase

import org.example.api.repository.StoresRepository
import org.example.domain.model.Store

class StoresUsecase(
    private val repository: StoresRepository = StoresRepository(),
) {
    fun getStores(): List<Store> = repository.getStores()
}
