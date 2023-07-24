package io.invoicex.mob2con.data.persistence

import io.invoicex.mob2con.domain.model.Drink

object DrinksDataStore {
    private val drinks: MutableList<Drink> = mutableListOf()

    fun setDrinks(list: List<Drink>) {
        this.drinks.addAll(list)
    }

    fun getDrinks() = this.drinks
}
