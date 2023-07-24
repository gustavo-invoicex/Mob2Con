package io.invoicex.mob2con.presentation.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import dagger.hilt.android.AndroidEntryPoint
import io.invoicex.mob2con.databinding.ValidQrCodeFragmentBinding
import io.invoicex.mob2con.presentation.home.HomeViewModel

@AndroidEntryPoint
class ValidQRCodeFragment : Fragment() {

    private lateinit var _binding: ValidQrCodeFragmentBinding
    private val binding get() = _binding

    private val viewModel: ValidQRCodeViewModel by viewModels()

    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ValidQrCodeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, binding.scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                viewModel.validateQRCode(it.text)
            }
        }
        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

        observeState()
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                ValidQRCodeState.SKUFoundAndDivergent -> showMessage("SKU Encontrado porém com valor divergente.")
                ValidQRCodeState.QRCodeInvalid -> showMessage("QR code inválido.")
                ValidQRCodeState.SKUIsValid -> showMessage("SKU Encontrado e valor corresponde ao cadastrado. ")
                ValidQRCodeState.SKUNotFound -> showMessage("SKU não encontrado na listagem.")
            }
            findNavController().popBackStack()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

}
