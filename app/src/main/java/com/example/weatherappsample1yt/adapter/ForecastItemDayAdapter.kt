package com.example.weatherappsample1yt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherappsample1yt.R
import com.example.weatherappsample1yt.databinding.ForecastViewholderDayitemBinding
import com.example.weatherappsample1yt.model.ForecastDailyData
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class ForecastItemDayAdapter :
    RecyclerView.Adapter<ForecastItemDayAdapter.ForecastItemDayViewHolder>() {
    private lateinit var binding: ForecastViewholderDayitemBinding

    inner class ForecastItemDayViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ForecastItemDayAdapter.ForecastItemDayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ForecastViewholderDayitemBinding.inflate(
            inflater, parent, false
        )
        return ForecastItemDayViewHolder()
    }

    override fun onBindViewHolder(
        holder: ForecastItemDayAdapter.ForecastItemDayViewHolder, position: Int
    ) {
        val binding = ForecastViewholderDayitemBinding.bind(holder.itemView)

        val forecast = differ.currentList[position]
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        val date = inputFormat.parse(forecast.date)
        val dayOfWeek = date?.let { outputFormat.format(it) }

        val icon = when (forecast.weatherIcon) {
            "01d", "0n" -> "sunny"
            "02d", "02n" -> "cloudy_sunny"
            "03d", "03n" -> "cloudy_sunny"
            "04d", "04n" -> "cloudy"
            "09d", "09n" -> "rainy"
            "10d", "10n" -> "rainy"
            "11d", "11n" -> "storm"
            "13d", "13n" -> "snow"
            "50d", "50n" -> "mist"
            else -> "sunny"
        }

        // Create a map of icon names to drawable resource IDs
        val iconMap: Map<String, Int> = mapOf(
            "sunny" to R.drawable.sunny_icon,
            "cloudy_sunny" to R.drawable.cloudy_sunny,
            "cloudy" to R.drawable.cloudy,
            "rainy" to R.drawable.rainy,
            "storm" to R.drawable.storm,
            "snow" to R.drawable.snowy,
            "mist" to R.drawable.haze
        )
        val drawableResourceId = iconMap[icon]

        try {
            binding.apply {
                dayText.text = dayOfWeek
                Glide.with(root.context).load(drawableResourceId).into(picWeatherDay)
                val maxTemp = forecast.maxTemp?.roundToInt() ?: 0
                val minTemp = forecast.minTemp?.roundToInt() ?: 0
                tempWeatherDayItem.text = holder.itemView.context.getString(
                    R.string.temp_weather_day_item, minTemp, maxTemp
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallBack = object : DiffUtil.ItemCallback<ForecastDailyData>() {
        override fun areItemsTheSame(
            oldItem: ForecastDailyData, newItem: ForecastDailyData
        ): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(
            oldItem: ForecastDailyData, newItem: ForecastDailyData
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}