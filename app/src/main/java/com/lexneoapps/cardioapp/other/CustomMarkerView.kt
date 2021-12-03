package com.lexneoapps.cardioapp.other

import android.content.Context
import android.view.LayoutInflater
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.lexneoapps.cardioapp.databinding.MarkerViewBinding
import com.lexneoapps.cardioapp.db.Cardio
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val cardios: List<Cardio>,
    c: Context,
    layoutId: Int
) : MarkerView(c, layoutId) {

    private var _binding: MarkerViewBinding? = null
    private val binding get() = _binding!!



    init { // inflate binding and add as view
        _binding = MarkerViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if(e == null) {
            return
        }
        val curCardioId = e.x.toInt()
        val cardio = cardios[curCardioId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = cardio.timeStamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(calendar.time)


        val avgSpeed = "${cardio.avgSpeedInKMH}km/h"
        binding.tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${cardio.distanceInMeters / 1000f}km"
        binding.tvDistance.text = distanceInKm

        binding.tvDuration.text = TrackingUtility.getFormattedStopWatchTime(cardio.timeInMillis)

        val caloriesBurned = "${cardio.caloriesBurned}kcal"
        binding.tvCaloriesBurned.text = caloriesBurned
    }
}