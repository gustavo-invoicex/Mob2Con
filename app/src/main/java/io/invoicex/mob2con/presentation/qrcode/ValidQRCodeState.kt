package io.invoicex.mob2con.presentation.qrcode

sealed class ValidQRCodeState {
    object SKUNotFound : ValidQRCodeState()
    object SKUFoundAndDivergent : ValidQRCodeState()
    object SKUIsValid : ValidQRCodeState()
    object QRCodeInvalid : ValidQRCodeState()
}
