package io.invoicex.mob2con.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.invoicex.mob2con.domain.repository.DrinksRepository
import io.invoicex.mob2con.presentation.home.state.HomeState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DrinksRepository
) : ViewModel() {

    private var _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState>
        get() = _state


    fun fetchDrinks() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            runCatching {
                val fetchDrinks = repository.fetchDrinks()
                _state.value = HomeState.Success(fetchDrinks)
            }.getOrElse {
                _state.value = HomeState.Error(it.localizedMessage.orEmpty())
            }
        }
    }

}