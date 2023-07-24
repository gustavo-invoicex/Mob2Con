package io.invoicex.mob2con.presentation.qrcode

import androidx.lifecycle.Observer
import io.invoicex.mob2con.BaseTest
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ValidQRCodeViewModelTest : BaseTest() {

    private lateinit var validQRCodeStateLiveDataObserver: Observer<ValidQRCodeState>
    private val viewModel = ValidQRCodeViewModel()

    @Before
    override fun setUp() {
        super.setUp()
        validQRCodeStateLiveDataObserver = mockk()
        viewModel.state.observeForever(validQRCodeStateLiveDataObserver)
    }

    @Test
    fun givenValidateQRCodeWhenInvokeThenShouldReturningSKUIsValid() {
        //given
        val qrCode = "1:10.00"
        val resourceSlot = slot<ValidQRCodeState>()
        every { validQRCodeStateLiveDataObserver.onChanged(capture(resourceSlot)) } just Runs

        //when
        viewModel.validateQRCode(qrCode)

        //then
        assertEquals(viewModel.state.value, resourceSlot.captured)

    }

    @Test
    fun givenValidateQRCodeWhenInvokeThenShouldReturningQRCodeInValid() {
        //given
        val qrCode = "asdasd"
        val resourceSlot = slot<ValidQRCodeState>()
        every { validQRCodeStateLiveDataObserver.onChanged(capture(resourceSlot)) } just Runs

        //when
        viewModel.validateQRCode(qrCode)

        //then
        assertEquals(viewModel.state.value, resourceSlot.captured)

    }

    @Test
    fun givenValidateQRCodeWhenInvokeThenShouldReturningSKUNotFound() {
        //given
        val qrCode = "10:90.00"
        val resourceSlot = slot<ValidQRCodeState>()
        every { validQRCodeStateLiveDataObserver.onChanged(capture(resourceSlot)) } just Runs

        //when
        viewModel.validateQRCode(qrCode)

        //then
        assertEquals(viewModel.state.value, resourceSlot.captured)

    }

    @Test
    fun givenValidateQRCodeWhenInvokeThenShouldReturningSKUFoundAndDivergent() {
        //given
        val qrCode = "1:90.00"
        val resourceSlot = slot<ValidQRCodeState>()
        every { validQRCodeStateLiveDataObserver.onChanged(capture(resourceSlot)) } just Runs

        //when
        viewModel.validateQRCode(qrCode)

        //then
        assertEquals(viewModel.state.value, resourceSlot.captured)

    }

}