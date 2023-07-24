package io.invoicex.mob2con.domain.model

import io.invoicex.mob2con.R

data class Drink(
    val id: Int,
    val name: String,
    val value: String
)

fun Drink.toImage() =
    when (this.id) {
        1 -> R.drawable.coca_cola
        2 -> R.drawable.sprite
        3 -> R.drawable.fanta_uva
        4 -> R.drawable.fanta_guarana
        5 -> R.drawable.pinga
        6 -> R.drawable.cerveja
        7 -> R.drawable.amarula
        else -> R.drawable.whisky
    }