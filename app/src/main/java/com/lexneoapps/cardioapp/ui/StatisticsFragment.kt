package com.lexneoapps.cardioapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lexneoapps.cardioapp.R
import com.lexneoapps.cardioapp.databinding.FragmentStatisticsBinding
import com.lexneoapps.cardioapp.other.TrackingUtility
import com.lexneoapps.cardioapp.ui.viewmodels.MainViewModel
import com.lexneoapps.cardioapp.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.round


@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {



    private var _binding: FragmentStatisticsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: StatisticsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
    }

    private fun subscribeToObservers(){
        viewModel.totalTimeRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                binding.tvTotalTime.text = totalTimeRun
            }
            viewModel.totalDistance.observe(viewLifecycleOwner, Observer {
                it?.let {
                    val km = it / 1000f
                    val totalDistance = round(km * 10f) / 10f
                    val totalDistanceString = "${totalDistance}km"
                    binding.tvTotalDistance.text = totalDistanceString
                }
            })
            viewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
                it?.let {
                    val avgSpeed = round(it * 10f) / 10f
                    val avgSpeedString = "${avgSpeed}km/h"
                    binding.tvAverageSpeed.text = avgSpeedString
                }
            })
            viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, Observer {
                it?.let {
                    val totalCalories = "${it}kcal"
                    binding.tvTotalCalories.text = totalCalories
                }
            })
        })
    }
}