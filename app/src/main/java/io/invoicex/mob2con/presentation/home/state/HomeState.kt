package io.invoicex.mob2con.presentation.home.state

import io.invoicex.mob2con.domain.model.Drink

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val data: List<Drink>) : HomeState()
    data class Error(val message: String) : HomeState()
}
