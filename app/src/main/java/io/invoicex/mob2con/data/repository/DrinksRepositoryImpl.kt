package io.invoicex.mob2con.data.repository

import io.invoicex.mob2con.data.mapper.DrinkMapper
import io.invoicex.mob2con.data.persistence.DrinksDataStore
import io.invoicex.mob2con.domain.model.Drink
import io.invoicex.mob2con.domain.repository.DrinksRepository
import io.invoicex.mob2con.network.service.SkuService
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(
    private val service: SkuService,
    private val mapper: DrinkMapper
) : DrinksRepository {

    override suspend fun fetchDrinks(): List<Drink> {
        val drinks = service.fetchSkus().map { mapper.responseToModel(it) }
        DrinksDataStore.setDrinks(drinks)
        return DrinksDataStore.getDrinks()
    }
}