package com.lexneoapps.cardioapp.ui


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lexneoapps.cardioapp.R
import com.lexneoapps.cardioapp.adapters.CardioAdapter
import com.lexneoapps.cardioapp.databinding.FragmentRunBinding
import com.lexneoapps.cardioapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run) {


    private var _binding: FragmentRunBinding? = null

    private lateinit var cardioAdapter: CardioAdapter

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    var isTrue = false

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.e("DEBUG", "${it.key} = ${it.value}")

                val granted = permissions.entries.all {
                    it.value == true
                }


            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRunBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        viewModel.cardioSortedByDate.observe(viewLifecycleOwner, Observer {
            cardioAdapter.submitList(it)
        })
        binding.fab.setOnClickListener {
            startRun()
        }
    }

    private fun setUpRecyclerView() = binding.rvRuns.apply {
        cardioAdapter = CardioAdapter()
        adapter = cardioAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun startRun() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) +
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            goToTrackingFragment()
        } else {
            requestPermission()
        }

    }

    private fun requestPermission() {

        val permissionsToRequest = mutableListOf<String>(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )


        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) +
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )


                    == PackageManager.PERMISSION_GRANTED -> goToTrackingFragment()

            (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

                    ) ->


                Snackbar.make(requireView(), "Grant permission", Snackbar.LENGTH_INDEFINITE)
                    .setAction("enable") {
                        requestMultiplePermissions.launch((permissionsToRequest.toTypedArray()))
                    }.show()

            else -> requestMultiplePermissions.launch(permissionsToRequest.toTypedArray())


        }
    }

    private fun goToTrackingFragment() {
        val action = RunFragmentDirections.actionRunFragmentToTrackingFragment()
        findNavController().navigate(action)
    }
}





