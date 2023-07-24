package io.invoicex.mob2con.domain.repository

import io.invoicex.mob2con.domain.model.Drink

interface DrinksRepository {
    suspend fun fetchDrinks(): List<Drink>
}