package io.invoicex.mob2con.data.mapper

import io.invoicex.mob2con.domain.model.Drink
import io.invoicex.mob2con.network.service.SkuResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkMapper @Inject constructor() {
    fun responseToModel(skuResponse: SkuResponse): Drink =
        Drink(
            id = skuResponse.id,
            name = skuResponse.name,
            value = skuResponse.value,
        )
}