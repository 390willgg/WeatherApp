package com.example.weatherappsample1yt.presentation.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherappsample1yt.R
import com.example.weatherappsample1yt.data.model.format.HourlyDetail
import com.example.weatherappsample1yt.databinding.ForecastViewholderBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ForeCastAdapter : RecyclerView.Adapter<ForeCastAdapter.ForeCastViewHolder>() {
    private lateinit var binding: ForecastViewholderBinding
    inner class ForeCastViewHolder : RecyclerView.ViewHolder(binding.root)

    private fun parseDate(dateString: String): Date? {
        val dateFormats = listOf(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        )

        for (format in dateFormats) {
            try {
                return format.parse(dateString)
            } catch (e: ParseException) {
                // Ignore and try the next format
                Log.e("ForeCastAdapter", "Failed to parse date", e)
                continue
            }
        }

        // All attempts to parse the date string failed
        return null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ForeCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ForecastViewholderBinding.inflate(inflater, parent, false)
        return ForeCastViewHolder()
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        val binding = ForecastViewholderBinding.bind(holder.itemView)
        val forecast = differ.currentList[position]
        val timeZone = TimeZone.getTimeZone(Locale.getDefault().toString())

        val date = parseDate(forecast.time.toString())
        val calendar = Calendar.getInstance(timeZone)
        calendar.timeInMillis = (date ?: Date()).time

        val currentDateTime = Calendar.getInstance(timeZone)

        val outputFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        val dayOfWeek = date?.let { outputFormat.format(it) }

        // Jika tanggal data sama dengan tanggal saat ini
        if (calendar.get(Calendar.DAY_OF_YEAR) == currentDateTime.get(Calendar.DAY_OF_YEAR) &&
            calendar.get(Calendar.YEAR) == currentDateTime.get(Calendar.YEAR)
        ) {
            // Tampilkan "Today" sebagai judul
            binding.titleText.text = "Today"
        } else {
            // Tampilkan tanggal sebagai judul
            binding.titleText.text = dayOfWeek
        }

        // Jika waktu data sama atau setelah waktu saat ini
        if (!calendar.time.before(currentDateTime.time)) {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            Log.d("hour", hour.toString())
            val timeType = if (hour < 12) "AM" else "PM"
            val hour12 = calendar.get(Calendar.HOUR)
            Log.d("hour12", hour12.toString())

            val icon = when (forecast.icon.toString()) {
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

            binding.apply {
                hourText.text = holder.itemView.context.getString(
                    R.string.temperatur_format, hour12, timeType
                )
                tempText.text = forecast?.temp?.let {
                    Math.round(it).toString() + "°"
                }

                Glide.with(root.context).load(drawableResourceId).into(pic)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallBack = object : DiffUtil.ItemCallback<HourlyDetail>() {
        override fun areItemsTheSame(
            oldItem: HourlyDetail, newItem: HourlyDetail
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HourlyDetail, newItem: HourlyDetail
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}