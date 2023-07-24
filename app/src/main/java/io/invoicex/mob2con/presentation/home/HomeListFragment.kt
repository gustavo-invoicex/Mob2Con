package io.invoicex.mob2con.presentation.home

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.invoicex.mob2con.R
import io.invoicex.mob2con.databinding.HomeListFragmentBinding
import io.invoicex.mob2con.domain.model.Drink
import io.invoicex.mob2con.presentation.home.state.HomeState
import io.invoicex.mob2con.util.GridSpacingItemDecoration

@AndroidEntryPoint
class HomeListFragment : Fragment() {
    private lateinit var _binding: HomeListFragmentBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDrinks()
        observeState()
        binding.mbValidQrCode.setOnClickListener {
            validatePermissions()
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeState.Error -> showMessage(state.message)
                HomeState.Loading -> showMessage("Loading")
                is HomeState.Success -> makeData(state.data)
            }
        }
    }

    private fun makeData(data: List<Drink>) {
        with(binding.rvDrinks) {
            binding.rvDrinks.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvDrinks.adapter = DrinkAdapter(data)
            binding.rvDrinks.removeItemDecorations()
            binding.rvDrinks.addItemDecoration(
                GridSpacingItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.h1),
                    2,
                    true
                )
            )
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun RecyclerView.removeItemDecorations() {
        while (this.itemDecorationCount > 0) {
            this.removeItemDecorationAt(0)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("TAG", "Tem Permisssao")
            } else {
                Log.d("TAG", "nao tem permissao")
            }
        }


    private fun validatePermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                findNavController().navigate(R.id.validQRCodeFragment)
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(requireContext(), "Precisamos da sua permissão", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }
}