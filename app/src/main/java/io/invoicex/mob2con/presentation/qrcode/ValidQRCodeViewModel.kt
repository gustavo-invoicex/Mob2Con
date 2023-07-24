package io.invoicex.mob2con.presentation.qrcode

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.invoicex.mob2con.data.persistence.DrinksDataStore
import javax.inject.Inject

@HiltViewModel
class ValidQRCodeViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableLiveData<ValidQRCodeState>()
    val state: LiveData<ValidQRCodeState>
        get() = _state

    fun validateQRCode(value: String) {
        val result = extract(value)
        when {
            qrInvalid(value) ->
                emit(ValidQRCodeState.QRCodeInvalid)

            idNotFound(result.first) ->
                emit(ValidQRCodeState.SKUNotFound)

            idFoundButValueInvalid(result) ->
                emit(ValidQRCodeState.SKUFoundAndDivergent)

            qrIsValid(result) ->
                emit(ValidQRCodeState.SKUIsValid)
        }
    }

    private fun emit(state: ValidQRCodeState) {
        this._state.value = state
    }

    private fun idNotFound(value: String) =
        DrinksDataStore.getDrinks()
            .filter { it.id.toString() == value }.isNullOrEmpty()

    private fun idFoundButValueInvalid(value: Pair<String, String>) =
        DrinksDataStore.getDrinks()
            .any { it.id.toString() == value.first && it.id.toString() != value.first }

    private fun qrIsValid(value: Pair<String, String>) =
        DrinksDataStore.getDrinks()
            .any { it.value == value.second && it.id.toString() == value.first }

    private fun qrInvalid(value: String) =
        !value.matches("[0-9]+;([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?".toRegex())

    @VisibleForTesting
    fun extract(value: String): Pair<String, String> {
        val split = value.split(";")
        return Pair(split.first(), split.last())
    }

}
