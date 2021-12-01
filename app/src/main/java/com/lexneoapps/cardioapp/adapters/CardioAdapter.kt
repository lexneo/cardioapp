package com.lexneoapps.cardioapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lexneoapps.cardioapp.databinding.ItemRunBinding
import com.lexneoapps.cardioapp.db.Cardio
import com.lexneoapps.cardioapp.other.TrackingUtility
import java.text.SimpleDateFormat
import java.util.*

class CardioAdapter : RecyclerView.Adapter<CardioAdapter.CardioViewHolder>() {

    class CardioViewHolder(val binding: ItemRunBinding) : RecyclerView.ViewHolder(binding.root)

    val diffCallback = object : DiffUtil.ItemCallback<Cardio>() {
        override fun areItemsTheSame(oldItem: Cardio, newItem: Cardio): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Cardio, newItem: Cardio): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Cardio>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardioViewHolder {
        return CardioViewHolder(
            ItemRunBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardioViewHolder, position: Int) {

        val cardio = differ.currentList[position]

        Glide.with(holder.binding.root).load(cardio.img).into(holder.binding.ivRunImage)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = cardio.timeStamp
        }


        holder.binding.apply {
            val dateFormat = SimpleDateFormat("dd.MM.yy",Locale.getDefault())
            tvDate.text = dateFormat.format(calendar.time)

            val avgSpeed = "${cardio.avgSpeedInKMH}km/h"
            tvAvgSpeed.text = avgSpeed

            val distanceInKm = "${cardio.distanceInMeters/1000}km"
            tvDistance.text = distanceInKm

            tvTime.text = TrackingUtility.getFormattedStopWatchTime(cardio.timeInMillis)

            val caloriesBurned = "${cardio.caloriesBurned}kcal"
            tvCalories.text = caloriesBurned
        }






    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}